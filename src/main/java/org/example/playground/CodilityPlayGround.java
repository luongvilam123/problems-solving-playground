package org.example.playground;

import java.util.*;

public class CodilityPlayGround {

    //Nab Interview Questions

    // 1: We are given a string S of length N consisting of letters 'A' and/or 'B'.
    // Our goal is to obtain a string in the format "A...AB...B"
    // (all letter 'A' occur before all letters 'B') by deleting some letters from S.
    // In particular, strings consisting only of letters 'A' or only letters 'B' fit this
    // format. Write a function that given a string S, returns the minimum
    // number of letters that need to be deleted from S in order to obtain
    // a string in the above format
        //BBABABAB ->AAAB
    public static int solution(String s) {
        int bCharacter = 0, validCharacter = 0;

        for (int i = 0 ; i< s.length(); i++) {
            if ('A' == s.toCharArray()[i]) {
                // Minimum deletions with this character included
                // is equal to count of all Bs before it
                validCharacter++;
                validCharacter = Math.min(bCharacter, validCharacter);

            } else {
                // Increment the count of 'B's
                bCharacter++;
                // There is no need to exclude the last 'B' at the end of
                // the string, the min_dels does not change
            }
        }

        return validCharacter;
    }
    public static int degreesOfSeparation(String[] connections, String person1, String person2) {
        // Create a map to store the connections
        Map<String, List<String>> graph = new HashMap<>();

        // Populate the graph with connections
        for (String connection : connections) {
            String[] names = connection.split(":");
            String name1 = names[0];
            String name2 = names[1];

            graph.putIfAbsent(name1, new ArrayList<>());
            graph.putIfAbsent(name2, new ArrayList<>());

            graph.get(name1).add(name2);
            graph.get(name2).add(name1);
        }
        // Perform BFS to find the degrees of separation
        return bfs(graph,person1, person2);
    }

    private static int bfs(Map<String, List<String>> graph, String start, String end) {
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.add(start);
        visited.add(start);

        int degree = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                String current = queue.poll();


                if (current.equals(end)) {
                    return degree;
                }

                for (String neighbor : graph.getOrDefault(current, new ArrayList<>())) {
                    if (!visited.contains(neighbor)) {
                        queue.add(neighbor);
                        visited.add(neighbor);
                    }
                }
            }

            degree++;
        }

        return -1;
    }

    public static int calculateCastles(int[] A) {
        int borderSegment = A.length;
        if (borderSegment == 0) return 0;
        int result = 0;
        int lastMinMaxHeight = A[0];
        for(int idx = 1; idx < borderSegment - 1; idx++) {
            if(((A[idx] - lastMinMaxHeight) * (A[idx + 1] - A[idx])) < 0) {
                result++;
                lastMinMaxHeight = A[idx];
            }
        }
        if(result == 0){
            if(A[0] == A[borderSegment-1]) return 1;
            return 2;
        }
        return result + 2;
    }
    public static int fixPotholes(String s, int B) {
        int n = s.length();
        int fixHole = 0;
        int totalCost = 0;
        int maxConsecutiveHoles = 0;

        for (int i = 0; i < n; i++) {
            if('x' == s.charAt(i)){
                maxConsecutiveHoles++;
            }
            if(maxConsecutiveHoles == 1 && '.' == s.charAt(i) ) {
                maxConsecutiveHoles = 0;
                continue;
            }
            if('.' == s.charAt(i) && maxConsecutiveHoles > 1) {
                if(totalCost <= B){
                    totalCost = totalCost + maxConsecutiveHoles + 1;
                    fixHole = fixHole + maxConsecutiveHoles;
                    maxConsecutiveHoles = 0;
                } else {
                    return fixHole;
                }
            }
        }
        if(maxConsecutiveHoles > 1 && (totalCost + maxConsecutiveHoles + 1) <= B){
            fixHole = fixHole + maxConsecutiveHoles;
        } else {
            fixHole = fixHole + (B - totalCost - 1);
        }

        return fixHole;
    }

    public static int removeDuplicatesFromSortedArray2(int[] nums) {
        int j = 1;
        int n = nums.length;
        int count = 1;
        for(int i = 1; i < n; i++) {
            if( nums[i] == nums[i-1] && count < 2) {
                nums[j] = nums[i];
                count++;
                j++;
            } else if(nums[i] != nums[i-1]){
                count = 1;
                nums[j] =nums[i];
                j++;
            }

        }
        return j;
    }
    public static boolean isPalindrome(String s) {
        if (s.isEmpty()) {
            return true;
        }
        int start = 0;
        int last = s.length() - 1;
        while(start <= last) {
            char currFirst = s.charAt(start);
            char currLast = s.charAt(last);
            if (!Character.isLetterOrDigit(currFirst )) {
                start++;
            } else if(!Character.isLetterOrDigit(currLast)) {
                last--;
            } else {
                if (Character.toLowerCase(currFirst) != Character.toLowerCase(currLast)) {
                    return false;
                }
                start++;
                last--;
            }
        }
        return true;
    }
    // https://leetcode.com/problems/longest-substring-without-repeating-characters/?envType=study-plan-v2&envId=top-interview-150
    public int lengthOfLongestSubstring(String s) {
        int leftPointer = 0, rightPointer = 0, longestLength = 0;
        boolean[] visitedChar = new boolean[256];

        while(rightPointer < s.length()){
            if(visitedChar[s.charAt(rightPointer)]){
                while(visitedChar[s.charAt(rightPointer)]){
                    visitedChar[s.charAt(leftPointer)] = false;
                    leftPointer++;
                }
            }
            visitedChar[s.charAt(rightPointer)] = true;
            longestLength = Math.max(longestLength, (rightPointer - leftPointer) + 1);
            rightPointer++;
        }
        return longestLength;
    }
//    https://leetcode.com/problems/is-subsequence/?envType=study-plan-v2&envId=top-interview-150
    public boolean isSubsequence(String s, String t) {
        int left = 0, right = 0, valid = 0;
        while(left < s.length() && right < t.length()){
            if(s.charAt(left) == t.charAt(right)){
                valid++;
                left++;
            }
            right++;
        }
        return s.length() == valid;
    }
    public static void main(String [] args){
        String[] connections = {"fred:joe", "joe:mary", "mary:fred", "mary:bill"};
        String person1 = "fred";
        String person2 = "bill";
        int nums[]= {1,1,1,2,2,3};
        String result = "0P";
        System.out.print(isPalindrome(result));
          System.out.print(isPalindrome(result));
//        System.out.println(removeDuplicatesFromSortedArray2(nums));
//        int result = degreesOfSeparation(connections, person1, person2);
//        System.out.println(calculateCastles(new int[]{2, 2, 3, 4, 3, 3, 2, 2, 1, 1, 2, 5}));
//        System.out.println(fixPotholes("...xxx..x....xxx",7)); //5
//        System.out.println(fixPotholes("..xxxxx",4)); // 3
//        System.out.println(fixPotholes("...",14)); // 0
    }
}
