package org.example.playground;

import java.util.*;
import java.util.stream.Collectors;

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

    //String[] connections = {"fred:joe", "joe:mary", "mary:fred", "mary:bill"};
    public static int degreesOfSeparation(String[] connections, String person1, String person2) {
        // Create a map to store the connections
        Map<String, List<String>> graph = new HashMap<>();
        Set<String> visited = new HashSet<>();
        int result = 0;
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
       //return dfs2(graph,person1, person2,visited,result);
    }

    private static int bfs(Map<String, List<String>> graph, String start, String end) {
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.add(start);
        visited.add(start);

        int degree = 0;

        while (!queue.isEmpty()) {
            int levelSize = queue.size();  // Track the number of nodes at the current level
            for (int i = 0; i < levelSize; i++) {
                String current = queue.poll();
                System.out.println(current);
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

    private static int dfs(Map<String, List<String>> graph, String start, String end, Set<String> visited,int result) {
        visited.add(start);

        for(String friend : graph.getOrDefault(start,new ArrayList<>())){
            System.out.println(start +" connect to "+ graph.getOrDefault(start,new ArrayList<>()));
            if(friend.equals(end)){
                System.out.println(start + " have connected with " + end);
                return result + 1;
            }
            if(!visited.contains(friend)){
                int temp = dfs(graph,friend,end,visited,result);
                if(temp != -1) {
                    return result;
                }
            }
        }
        return -1;
    }

    private static int dfs2(Map<String, List<String>> graph, String start, String end, Set<String> visited,int result) {
        Stack<String> stack = new Stack<>();
        stack.push(start);
        visited.add(start);
        while(!stack.empty()){
            String you = stack.pop();
            System.out.println(you);
            if( you.equals(end)){
                return result;
            }
            for(String friend : graph.getOrDefault(you, new ArrayList<>())){
                if(!visited.contains(friend)){
                    visited.add(friend);
                    stack.push(friend);
                    result++;
                }
            }
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

    //https://leetcode.com/problems/is-subsequence/?envType=study-plan-v2&envId=top-interview-150
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
    public static int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int i = 0; i < k; i++) {
            minHeap.offer(nums[i]);
        }

        for (int i = k; i < nums.length; i++) {
            if (nums[i] > minHeap.peek()) {
                minHeap.poll();
                minHeap.offer(nums[i]);
            }
        }
        return minHeap.peek();
    }

    public static String longestCommonPrefix(String[] strs) {
        if (strs.length == 1) {return strs[0];}
        StringBuilder sb1 = new StringBuilder();
        Arrays.sort(strs);
        int n = Math.min(strs[0].length(),strs[strs.length -1].length());
        for(int i = 0 ; i < n ; i++ ){
            if(strs[0].charAt(i) != strs[strs.length -1].charAt(i)){
                return sb1.toString();
            }
            sb1.append(strs[0].charAt(i));
        }
        return sb1.toString();
    }

    // String[] rowDatas = {"1,Ben,0","2,Kate,1","3,Damien,2","4,Jane,1","5,Meng,4"};
    public static void printOrganizeChart(String[] array){
        HashMap<Integer,ArrayList<String>> organizeChart = new HashMap<>();
        HashMap<String, Integer> nameToId = new HashMap<>();
        Set<String> visited = new HashSet<>();
        for (String data : array) {
            String[] rowData = data.split(",");
            organizeChart.putIfAbsent(Integer.valueOf(rowData[2]),new ArrayList<>());
            nameToId.putIfAbsent(rowData[1],Integer.valueOf(rowData[0]));
            if(organizeChart.containsKey(Integer.valueOf(rowData[2]))){
                    organizeChart.get(Integer.valueOf(rowData[2])).add(rowData[1]);
            }
        }
        Stack<String> stack = new Stack<>();
        String boss = "";
        for (Integer employeePriority: organizeChart.keySet() ) {
            if( employeePriority == 0){
                boss = organizeChart.get(employeePriority).get(0);
            }
        }
        stack.add(boss);
        visited.add(boss);

        while(!stack.empty()){
            String currentNode = stack.pop();
            System.out.println(currentNode);
            for (String employeeId: organizeChart.getOrDefault(nameToId.get(currentNode),new ArrayList<>())) {
                if(!visited.contains(employeeId)){
                    visited.add(employeeId);
                    stack.add(employeeId);
                }
            }
        }
    }

    public static boolean isAnagram(String s, String t) {
        HashMap<Character,Integer> trackingS = new HashMap<>();
        HashMap<Character,Integer> trackingT = new HashMap<>();
        for(char c : s.toCharArray()){
            trackingS.put(c,trackingS.getOrDefault(c,0));
        }
        for(char b : t.toCharArray()){
            trackingT.put(b,trackingT.getOrDefault(b,0));
        }
        if(t.length() < s.length()) return false;
        for(int i = 0 ; i < t.length() ; i ++){
            if( !s.contains(String.valueOf(t.charAt(i))) ||
                    Objects.equals(trackingS.get(t.charAt(i)), trackingT.get(t.charAt(i))) ) return false;
        }
        return true;
    }

    public static int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int pre[] = new int[n];
        int suff[] = new int[n];
        pre[0] = 1;
        suff[n - 1] = 1;

        for(int i = 1; i < n; i++) {
            pre[i] = pre[i - 1] * nums[i - 1];
        }
        for(int i = n - 2; i >= 0; i--) {
            suff[i] = suff[i + 1] * nums[i + 1];
        }

        int ans[] = new int[n];
        for(int i = 0; i < n; i++) {
            ans[i] = pre[i] * suff[i];
        }
        return ans;
    }

    //https://leetcode.com/problems/valid-sudoku/description/
    public boolean isValidSudoku(char[][] board) {
        HashSet<Object> hashSet = new HashSet<>();
        for (int i = 0; i < 9 ; i++) {
            for (int j = 0; j < 9 ; j++){
                // Get the current char in Sudoku Board
                char currentBoard = board[i][j];
                // If the current char is empty, continue to the next element
                if (currentBoard == '.') continue;
                // Check if the element in the row is unique
                if(!hashSet.add(currentBoard + "row" + i )) return false;
                // Check if the element in the col is unique
                if(!hashSet.add(currentBoard + "col" + j)) return false;
                // Check if the element in each square box is unique
                if(!hashSet.add(currentBoard + "square" + i/3 + "-" + j/3)) return false;
            }
        }
        return true;
    }

    //https://neetcode.io/problems/longest-consecutive-sequence
    public int longestConsecutive(int[] nums) {
        // Add array elements to Set for checking contains number
        Set<Integer> hashSet = Arrays.stream(nums).boxed().collect(Collectors.toSet());
        // longest consecutive length
        int result = 0;

        for (Integer num : hashSet) {
            // init consecutive length
            int length = 0;
            // if the current number do not have the previous value
            // contains in Set then it is the beginning of a consecutive length => increase length by one
            if (hashSet.contains(num -1)) length ++;
            // if the current number have the next number value in Set then increase the length by one
            while(hashSet.contains(num + length)){
                length = length + 1;
            }
            // Get the longest consecutive length by Max
            result = Math.max(length,result);
        }
        return result;
    }

    //https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/
    public int[] twoSum(int[] numbers, int target) {
        if(numbers.length == 0) return new int[]{};
       int left = 0;
       int right = numbers.length - 1;
       while (left < right){
           if( numbers[left] + numbers[right] == target){
               return new int[]{left+1,right+1};
               // check if the sum is greater than target -> shifting the right pointer to the left
           } else if(numbers[left] + numbers[right] > target){
               right--;
           } else {
               // if the sum is smaller than target -> shifting the left pointer to the right
               left ++;
           }
       }
        return new int[]{};
    }

    //https://leetcode.com/problems/3sum/description/
    public static List<List<Integer>> threeSum(int[] nums) {
        // Create result to contain all the triplets and
        // sort the array for the increase and decrease left and right pointers
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        // loop through the elements
        for(int i = 0; i < nums.length; i++){
            // Check if the first value i is duplicate, continue to the next element
            if( i > 0 && nums[i] == nums[i-1]) continue;
            // Create left and right pointers
            int left = i + 1;
            int right = nums.length -1;
            // loop through array and move left and right pointers
            while (left < right) {
                // Init sum value of the triplet
                int totalSum = nums[i] + nums[left] + nums[right];

                if (totalSum == 0) {
                    // if three element is matched with the condition -> at the triplet to the result
                    result.add(new ArrayList<>(Arrays.asList(nums[i], nums[left], nums[right])));
                    // if there is duplicate element when moving left pointer, move left pointer to next element
                    // if we founded the target triplet, move left pointer to the next element
                    do left++;
                    while (left < right && nums[left] == nums[left - 1]);
                } else if (totalSum > 0) {
                    right--;
                } else {
                    left++;
                }
            }
        }
        return result;
        }

    //https://leetcode.com/problems/container-with-most-water/description/
    public int maxArea(int[] height) {
        int result = 0;
        //Init left = 0 and right = length -1 pointers because we want the maximum length of the y-axis
        int left = 0;
        int right = height.length -1;

        while(left < right) {
            // get the height (x-axis) of the pointer
            int leftHeight = height[left];
            int rightHeight = height[right];
            // Calculate the max area
            int area = ( right - left ) * Math.min(leftHeight,rightHeight);
            // area is max then replace it with result
            result = Math.max(result,area);
            // if the height (x-axis) of the pointer that is higher than the other because we want the area to be max
            if(leftHeight < rightHeight) left++;
            else right --;
        }
        return result;
    }
    //https://leetcode.com/problems/trapping-rain-water/
    public int trap(int[] height) {
        int result = 0;
        // init two pointers left and right
        int left = 0, right = height.length - 1;
        // Get the max value of the current pointer left and right
        int maxLeft = height[left];
        int maxRight = height[right];

        while(left < right) {
            // we move the pointer if the pointer value of left is min
            if( maxLeft < maxRight) {
                left++;
                maxLeft = Math.max(maxLeft,height[left]);
                // calculate the trap water using this formula : maxLeft - height[left]
                result = result + ( maxLeft - height[left] );
                // we move the pointer if the pointer value of right is min
            } else {
                right--;
                maxRight = Math.max(maxRight,height[right]);
                // calculate the trap water using this formula : maxRight - height[right]
                result += maxRight -height[right];
            }
        }
        return result;
    }

    public boolean isValid(String s) {
        Stack<Character> parentheses = new Stack<Character>();
        if(s.length() == 1) return false;
        for(int i = 0 ; i < s.length(); i++){
            if(s.charAt(i) == '(' || s.charAt(i) == '{' || s.charAt(i) == '['){
                parentheses.push(s.charAt(i));
            } else {
                if(parentheses.empty()) return !parentheses.empty();
                else {
                    Character topChar = parentheses.peek();
                    if (s.charAt(i) == ')' && topChar == '(')
                        parentheses.pop();
                    else if (s.charAt(i) == ']' && topChar == '[')
                        parentheses.pop();
                    else if (s.charAt(i) == '}' && topChar == '{')
                        parentheses.pop();
                    else
                        parentheses.push(s.charAt(i));
                }
            }
        }
        return parentheses.empty();
    }

    public static void main(String[] args) {
//        String[] connections = {"fred:joe", "joe:mary", "mary:fred", "mary:bill"};
//        String person1 = "fred";
//        String person2 = "bill";
//        int nums[]= {1,1,1,2,2,3};
//        String result = "0P";
//        System.out.print(isPalindrome(result));
//        findKthLargest(new int[]{3, 2, 1, 5, 6, 4},2);
//        System.out.print(longestCommonPrefix(new String []{"adc", "ac","accd"}));
//        System.out.println(removeDuplicatesFromSortedArray2(nums));
//        int result = degreesOfSeparation(connections, person1, person2);
//          String[] rowDatas = {"1,Ben,0","2,Kate,1","3,Damien,2","4,Jane,1","5,Meng,4"};
//          printOrganizeChart(rowDatas);
//        System.out.println(calculateCastles(new int[]{2, 2, 3, 4, 3, 3, 2, 2, 1, 1, 2, 5}));
//        System.out.println(fixPotholes("...xxx..x....xxx",7)); //5
//        System.out.println(fixPotholes("..xxxxx",4)); // 3
//        System.out.println(fixPotholes("...",14)); // 0
//        System.out.println(isAnagram("rat","cat"));
//        System.out.println(Arrays.toString(productExceptSelf(new int[]{1,2,3,4})));
    }
}
