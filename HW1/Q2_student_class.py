class Student(object):
    def __init__(self, name, GPA, age):
        self.name = name
        self.GPA = GPA
        self.age = age

    def __str__(self):
        return "Student: " + self.name

    def __lt__(self, student_b):
        if(self.GPA == student_b.GPA):
            if(self.name == student_b.name):
                return self.age < student_b.age
            else:
                return self.name < student_b.name
        else:
            return (self.GPA < student_b.GPA)

    def __eq__(self, student_b):
        return (self.name, self.GPA, self.age) == (student_b.name, student_b.GPA, student_b.age)

    def __hash__(self):
        return hash((self.name, self.GPA, self.age))

    def __repr__(self):
        return repr((self.name, self.GPA, self.age))

    def __ne__(self, student_b):
        return not(self == student_b)


def testing(*args, **kwargs):
    """
    testing student class
    """

    student1 = Student('A', 4.0, 20)
    student2 = Student('A', 4.0, 21)
    student3 = Student('C', 3.9, 22)
    student4 = Student('E', 3.2, 20)
    student5 = Student('D', 3.2, 19)

    # testing sorted() and dict()
    student_list = [student2, student1 , student3, student4, student5]

    new_list1 = sorted(student_list)
    print(new_list1)

    student_dict = dict([(1, student1), (2, student2), (3, student3)])
    assert (student_dict[1].age == 20)
    assert (student_dict[2].name == 'A')
    assert (student_dict[3].GPA == 3.9)

    # array of 5 students with increasing GPA
    new_list2 = sorted(student_list, key=lambda s: (s.GPA, s.name, s.age))
    print(new_list2)
    assert(new_list1 == new_list2)

testing()