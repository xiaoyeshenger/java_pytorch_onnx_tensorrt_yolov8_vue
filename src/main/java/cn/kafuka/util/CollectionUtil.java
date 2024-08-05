package cn.kafuka.util;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class CollectionUtil {

    //判断children是否属于parent的子集
    public static boolean isSubset(List<Long> parent,List<Long> children) {
        List<Long> parent_1 = new ArrayList<>();
        parent_1.addAll(parent);

        List<Long> children_1 = new ArrayList<>();
        children_1.addAll(children);

        int differ = parent_1.size() - children_1.size();

        parent_1.removeAll(children_1);

        if(differ >= 0 && differ == parent_1.size()) {
            return true;
        }else {
            return false;
        }
    }

/*    public static void main(String[] args) {
        List<Long> idList1 = new ArrayList();
        List<Long> idList2= new ArrayList();
        idList1.add(2L);
        idList1.add(6L);

        idList2.add(1L);
        //idList2.add(6L);
        boolean disjoint = isSubset(idList1, idList2);
        System.out.println("是否子集:"+disjoint);

    }*/

}
