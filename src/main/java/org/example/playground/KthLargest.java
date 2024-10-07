package org.example.playground;

import java.util.PriorityQueue;

class KthLargest {
    PriorityQueue<Integer> integerQueue = new PriorityQueue<>();
    int k;
    public KthLargest(int k, int[] nums) {
        this.k = k;
        int n = nums.length;
        for( int i = 0 ; i < n ; i ++){
            integerQueue.add(nums[i]);
        }
        while (integerQueue.size() > k){
            integerQueue.poll();
        }
    }

    public int add(int val) {
        integerQueue.add(val);
        while (integerQueue.size() > k){
            integerQueue.poll();
        }
        return !integerQueue.isEmpty() ? integerQueue.peek() : 1;
    }
}
