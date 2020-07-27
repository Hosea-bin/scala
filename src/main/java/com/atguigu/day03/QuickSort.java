package com.atguigu.day03;

import java.sql.SQLOutput;
import java.util.Arrays;

public class QuickSort {
    private static int count;
    public static void main(String[] args) {
        int num[]={3,45,5,6,114,24,56,42,33,41,89};
        System.out.println(Arrays.toString(num));
        System.out.println(arrayToString(num,"未排序"));
        QKSort(num,0,num.length-1);
        System.out.println(arrayToString(num,"排序"));
        System.out.println("数组个数为:"+num.length);
        System.out.println("循环小次数为："+count);

    }

    private static void QKSort(int[] num,int left,int right){
        //left等于right,数组只有一个元素，直接返回
        if (left>=right){
            return;
        }
        //设置左边的元素为基准值
        int key = num[left];
        //数组中比key小的放左边，比key大的放右边，key的下标为i
        int i = left;
        int j = right;
        while (i<j){
            //j向左移动，直到遇到比key小的值
            while (num[j]>key&&i<j){
                j--;
            }
            //i向右移动，直到遇到比key大的值
            while (num[i]<=key&&i<j){
                i++;
            }
            //i和j指向的元素交换
            if(i<j){
                int temp = num[i];
                num[i] = num[j];
                num[j] = temp;
            }
        }
        num[left]=num[i];
        num[i]=key;
        count++;
        QKSort(num,left,i-1);
        QKSort(num,i+1,right);
    }

    /**
     * 将一个int类型数组转换为字符串
     */
    private static String arrayToString(int[] arr,String flag){
        String str = "数组为（"+flag+"):";
        for(int a : arr){
            str +=a+"\t";
        }
        return str;
    }
}
