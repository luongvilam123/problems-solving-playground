package org.example.playground;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CodilityPlayGroundTest {
//    String[] connections = {"fred:joe", "joe:mary", "mary:fred", "mary:bill"};
//    String person1 = "fred";
//    String person2 = "bill";
//    int nums[]= {1,1,1,2,2,3};
//    findKthLargest(new int[]{3, 2, 1, 5, 6, 4},2);
//        System.out.print(longestCommonPrefix(new String []{"adc", "ac","accd"}));
//        System.out.println(removeDuplicatesFromSortedArray2(nums));
//    int result = degreesOfSeparation(connections, person1, person2);
//    String[] rowDatas = {"1,Ben,0","2,Kate,1","3,Damien,2","4,Jane,1","5,Meng,4"};
//    printOrganizeChart(rowDatas);
//        System.out.println(fixPotholes("...xxx..x....xxx",7)); //5
//        System.out.println(fixPotholes("..xxxxx",4)); // 3
//        System.out.println(fixPotholes("...",14)); // 0
//        System.out.println(isAnagram("rat","cat"));
//        System.out.println(Arrays.toString(productExceptSelf(new int[]{1,2,3,4})));
//    dailyTemperaturess(new int[]{30,40,50,60});
//        System.out.println(true && true);
//        System.out.println(solution("BAAABAB"));
    @org.junit.jupiter.api.Test
    void calculateCastles() {
         int result = CodilityPlayGround.calculateCastles(new int[]{2, 2, 3, 4, 3, 3, 2, 2, 1,1,2,5});
         assertEquals(4, result);
    }

    @org.junit.jupiter.api.Test
    void calculateCastles2() {
        int result = CodilityPlayGround.countHillValley(new int[]{2, 2, 3, 4, 3, 3, 2, 2, 1,1,2,5});
        assertEquals(4, result);
    }

    @org.junit.jupiter.api.Test
    void calculateCastles3() {
        int result = CodilityPlayGround.countHillValley(new int[]{6,2,2,1,1,3,1,1});
        assertEquals(4, result);
    }
}