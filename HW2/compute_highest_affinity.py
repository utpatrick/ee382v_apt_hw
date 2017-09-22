# No need to process files and manipulate strings - we will
# pass in lists (of equal length) that correspond to 
# sites views. The first list is the site visited, the second is
# the user who visited the site.

# See the test cases for more details.

def highest_affinity(site_list, user_list, time_list):
  # Returned string pair should be ordered by dictionary order
  # I.e., if the highest affinity pair is "foo" and "bar"
  # return ("bar", "foo").

    dict = {}
    for i in range(0, len(site_list)):

        site = site_list[i]
        user = user_list[i]

        if dict.get(site) == None:
            dict[site] = set()
            dict[site].add(user)
        else:
            dict[site].add(user)

    listAff = []
    highAff = 0

    for key in dict.keys():
        users1 = dict[key]
        for key2 in dict.keys():
            users2 = dict[key2]
            if key2 is key:
                continue
            else:
                aff = len(users2 & users1)
                if aff > highAff:
                    highAff = aff
                    listAff.clear()
                    listAff.extend((key, key2))

    listAff.sort()
    #print(listAff)

    return (listAff[0], listAff[1])
