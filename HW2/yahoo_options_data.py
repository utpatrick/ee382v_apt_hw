import json
import sys
import re
import urllib
from bs4 import BeautifulSoup

dict_template = {'Ask': None, 'Bid': None, 'Change': None,
                 'Date': None, 'Last': None, 'Open': None,
                 'Strike': None, 'Symbol': None, 'Type': None,
                 'Vol': None, 'sortOpen': None}

def contractAsJson(filename):

    f = open(filename, 'r')
    soup = BeautifulSoup(f, 'html.parser')
    jsonTemp = {}
    jsonQuoteData = "[]"

    price_box = soup.find('span', attrs={'class': 'time_rtq_ticker'})
    price = float(price_box.text)

    finance_domain = "http://finance.yahoo.com"
    pattern_herf = re.compile(".*\?.=.*&.*=.*-.*")
    href_box = soup.find_all('a', href = pattern_herf)
    href_dates = []

    for href in href_box:
        href_sep = str(href).split("\"")
        href_dates.append(href_sep[1])

    for i in range(0, len(href_dates)):
        href_dates[i] = finance_domain + href_dates[i]

    callput_all = soup.find_all('tr')
    pattern_call_options = re.compile("Call Options")
    pattern_put_options = re.compile("Put Options")

    call_box = []
    put_box = []

    flag = 0

    for item in callput_all:
        if(pattern_call_options.search(str(item))):
            flag = 1
        if(pattern_put_options.search(str(item))):
            flag = 2
        if(flag == 1):
            call_box.append(str(item))
        if(flag == 2):
            put_box.append(str(item))

    call_list = []
    put_list = []
    pattern_callput1 = re.compile("^<tr><td class=\"yfnc_h\" nowrap=\"\">.*</tr>$")
    pattern_callput2 = re.compile("^<tr><td class=\"yfnc_tabledata1\" nowrap=\"\">.*</tr>$")
    for call in call_box:
        if(pattern_callput1.search(call) != None):
            call_list.append(call)
        if (pattern_callput2.search(call) != None):
            call_list.append(call)

    for put in put_box:
        if(pattern_callput1.search(put) != None):
            put_list.append(put)
        if (pattern_callput2.search(put) != None):
            put_list.append(put)

    call_dict_list = []
    put_dict_list = []

    for call in call_list:
        pattern_element = re.compile("</td>")
        element = pattern_element.split(call)
        call_dict = {}
        call_dict.fromkeys(dict_template)
        theParse(element, call_dict, "C")
        call_dict_list.append(call_dict)


    for put in put_list:
        pattern_element = re.compile("</td>")
        element = pattern_element.split(put)
        put_dict = {}
        put_dict.fromkeys(dict_template)
        theParse(element, put_dict, "P")
        put_dict_list.append(put_dict)

    for elem in put_dict_list:
        call_dict_list.append(elem)

    ans_list = sorted(call_dict_list, key=lambda k: int(''.join(k['Open'].split(','))), reverse=True)

    jsonTemp['currPrice'] = price
    jsonTemp['dateUrls'] = href_dates
    jsonTemp['optionQuotes'] = ans_list

    jsonQuoteData = json.dumps(jsonTemp, indent=4)

    f.close()

    with open('result.json', 'w') as fp:
        json.dump(jsonTemp, fp, sort_keys=True, indent=4)

    return jsonQuoteData

def theParse(element, targetDict, type):

    for i in range(0,8):
        targetDict['Type'] = type

        if i == 0:
            pattern_strike = re.compile("[0-9.]+[0-9.]")
            strike = pattern_strike.findall(element[i])
            targetDict['Strike'] = strike[1]

        if i == 1:
            pattern_date = re.compile("(1[0-9]+).[0-9]+")
            date = pattern_date.findall(element[i])
            targetDict['Date'] = date[1]

            pattern_symbol1 = re.compile("href=\"\/q\?s=(.*[0-9]+.[0-9]+)\">")
            symbol1 = pattern_symbol1.findall(element[i])
            symbol2 = symbol1[0].split(date[1])
            targetDict['Symbol'] = symbol2[0]

        if i == 2:
            pattern_last = re.compile("[0-9.]+[0-9.]+")
            last = pattern_last.findall(element[i])

            if (last == []):
                targetDict['Last'] = "N/A"
            else:
                targetDict['Last'] = last[0]

        if i == 3:
            pattern_change = re.compile(">([0-9.]+[0-9.]+)")
            change = pattern_change.findall(element[i])
            pattern_sign = re.compile("<b style=\"color:#(.*[0-9]+);\">")
            sign = pattern_sign.findall(element[i])
            if sign[0] == 'cc0000':
                targetDict['Change'] = "-" + change[0]
            elif sign[0] == '008800':
                targetDict['Change'] = "+" + change[0]
            else:
                targetDict['Change'] = " " + change[0]

        if i == 4:
            pattern_bid = re.compile("[0-9]+.[0-9]+")
            bid = pattern_bid.findall(element[i])
            if (bid == []):
                targetDict['Bid'] = "N/A"
            else:
                targetDict['Bid'] = bid[0]

        if i == 5:
            pattern_ask = re.compile("[0-9]+.[0-9]+")
            ask = pattern_ask.findall(element[i])
            if (ask == []):
                targetDict['Ask'] = "N/A"
            else:
                targetDict['Ask'] = ask[0]

        if i == 6:
            pattern_vol = re.compile(">([0-9]+.*)")
            vol = pattern_vol.findall(element[i])
            '''
            volList = vol[0].split(",")
            vol = ''.join(volList)
            '''
            targetDict['Vol'] = vol[0]

        if i == 7:
            pattern_openInt = re.compile(">([0-9]+.*)")
            openInt = pattern_openInt.findall(element[i])
            openList = openInt[0].split(",")
            sortOpen = ''.join(openList)
            targetDict['Open'] = openInt[0]
            #targetDict['sortOpen'] = int(sortOpen)

def checkNone(target):
    if target == None:
        target = "N/A"