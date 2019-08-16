package com.ivyzh.datastructures.hashtable;

/**
 * 案例：
 * 看一个实际需求，google公司的一个上机题:
 * 有一个公司,当有新的员工来报道时,要求将该员工的信息加入(id,性别,年龄,住址..),当输入该员工的id时,要求查找到该员工的 所有信息.
 * <p>
 * 要求:
 * 1.	不使用数据库,尽量节省内存,速度越快越好=>哈希表(散列)
 * 2.	添加时，保证按照id从低到高插入  [课后思考：如果id不是从低到高插入，但要求各条链表仍是从低到高，怎么解决?]
 * 3.	使用链表来实现哈希表, 该链表不带表头[即: 链表的第一个结点就存放雇员信息]
 * 4.	代码实现[增删改查(显示所有员工，按id查询)]
 */
public class HashTabDemo {
    public static void main(String[] args) {
        HashTab hashTab = new HashTab(8);
        for (int i = 0; i < 14; i++) {
            Emp emp = new Emp((i + 1), "zz" + (i + 1));
            hashTab.add(emp);
        }

        hashTab.list();

        Emp emp = hashTab.findEmpById(113);
        System.out.println(emp);

        hashTab.deleteEmpById(1);
        hashTab.deleteEmpById(9);
        hashTab.deleteEmpById(10);
        hashTab.deleteEmpById(7);
        hashTab.list();
    }


    static class HashTab {
        EmpLinkedList[] empLinkedList;
        int size;

        public HashTab(int size) {
            this.size = size;
            empLinkedList = new EmpLinkedList[size];

            for (int i = 0; i < size; i++) {
                empLinkedList[i] = new EmpLinkedList();
            }
        }

        public void add(Emp emp) {
            int i = hasFun(emp.no);
            empLinkedList[i].add(emp);
        }

        public int hasFun(int no) {
            return no % size;
        }

        public void list() {
            for (int i = 0; i < size; i++) {
                System.out.print("第" + i + "条链表-->");
                empLinkedList[i].list();
            }
        }

        public Emp findEmpById(int no) {
            int i = hasFun(no);
            return empLinkedList[i].findEmpById(no);
        }

        public void deleteEmpById(int no) {
            int i = hasFun(no);
            empLinkedList[i].deleteEmpById(no);
        }
    }

    static class EmpLinkedList {
        Emp header;

        public EmpLinkedList() {

        }

        public void add(Emp emp) {
            if (header == null) {
                header = emp;
                return;
            }
            Emp temp = header;

            while (true) {
                if (temp.next == null) {
                    break;
                }
                temp = temp.next;
            }
            temp.next = emp;
        }


        public void list() {
            if (header == null) {
                System.out.println("链表为空~~");
                return;
            }
            Emp temp = header;
            while (true) {
                if (temp == null) {
                    break;
                }
                System.out.print(temp + " -> ");
                temp = temp.next;
            }
            System.out.println();
        }

        public Emp findEmpById(int no) {
            if (header == null) {
                return null;
            }

            Emp temp = header;
            while (true) {
                if (temp == null) {
                    return null;
                }

                if (temp.no == no) {
                    return temp;
                }
                temp = temp.next;
            }
        }

        public void deleteEmpById(int no) {
            if (header == null) {
                return;
            }

            Emp before = header;
            Emp temp = header;
            boolean hasFind = false;

            while (true) {
                if (temp == null) {//找到了最后，都没有找到
                    break;
                }
                if (temp.no == no) {
                    hasFind = true;//找到了
                    break;
                }
                before = temp;
                temp = temp.next;
            }

            if (hasFind) {
                System.out.println("找到了，准备删除" + temp + ",before->" + before);
                if (temp == header) {//删除的是头节点
                    header = temp.next;
                } else {
                    before.next = temp.next;
                }
            } else {
                System.out.println("没有找到");
            }

        }
    }

    static class Emp {
        int no;
        String name;
        Emp next;

        public Emp(int no, String name) {
            this.no = no;
            this.name = name;
        }

        @Override
        public String toString() {
            return "Emp{" + "no=" + no + ", name='" + name + '\'' + '}';
        }
    }
}
