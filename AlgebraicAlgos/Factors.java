package AlgebraicAlgos;

import java.util.*;
public class Factors {
	
	
	HashMap<Integer, Integer> afactors(int n){
		HashMap<Integer,Integer> map = new HashMap<>();
		int k=2;
		while(n>1) {
			while(n%k ==0) {
				incrementCount(map,k);
				n/=k;
			}
			k++;
			
		}
		return map;
	}
	
	int sumOfFactors(int n){
		HashMap<Integer,Integer> map = afactors(n);
		int sum=0,pro=1;
		
		for(int key : map.keySet()) {
			sum=0;
			for(int i=0;i<= map.get(key);i++)
				sum += Math.pow(key, i);
			pro *= sum;
		}
		return pro;
	}
	int numberOfFactors(int n) {
		HashMap<Integer,Integer> map = afactors(n);
		int sum=1;
		
		for(int values : map.values())
			sum *= (values +1);
		return sum;
	}
	
	int sumOfEvenFactors(int n) {
		HashMap<Integer,Integer> map = afactors(n);
		int sum=0,pro=1;
		
		for(int key : map.keySet()) {
			sum=0;
			if(key%2==1)
				sum += Math.pow(key, 0);
			for(int i=1;i<= map.get(key);i++)
				sum += Math.pow(key, i);
			pro *= sum;
		}
		return pro;
	}
	
	int numberOfEvenFactors(int n) {
		HashMap<Integer,Integer> map = afactors(n);
		int sum=1;
		decrementCount(map,2);
		for(int values : map.values())
			sum *= (values +1);
		return sum;
	}
	
	int sumOfOddFactors(int n) {
		HashMap<Integer,Integer> map = afactors(n);
		int sum=0,pro=1;
		if(map.containsKey(2)) map.put(2, 0);
		for(int key : map.keySet()) {
			sum=0;
			for(int i=0;i<= map.get(key);i++)
				sum += Math.pow(key, i);
			pro *= sum;
		}
		return pro;
	}
	
	int numberOfOddFactors(int n) {
		HashMap<Integer,Integer> map = afactors(n);
		int sum=1;
		if(map.containsKey(2)) map.put(2, 0);
		for(int values : map.values())
			sum *= (values +1);
		return sum;
	}
	
	int sumOfFactorsDivisibleBy(int n,int m) {
		HashMap<Integer,Integer> cmap = afactors(m);
		HashMap<Integer,Integer> map = afactors(n);
		int sum=0,pro=1;
		System.out.println(cmap);
		for(int key : map.keySet()) {
			sum=0;
			for(int i=0;i<= map.get(key);i++) {
				if(cmap.containsKey(key)) {
					System.out.println(cmap);
					if(cmap.get(key)<=0) continue;
					System.out.println(cmap + " echec");
					sum += Math.pow(key, i);
					decrementCount(cmap,key);
				}
				else
					sum += Math.pow(key, i);
				
				if(key==5)
				System.out.println(sum + " for 5");
				}
			pro *= sum;
		}
		return pro;
		
	}
	
	int numbersOfFactorsDivisibleBy(int n , int m) {
		HashMap<Integer,Integer> cmap = afactors(m);
		HashMap<Integer,Integer> map = afactors(n);
		
		int sum=1;
		
		for(int values : map.values())
			sum *= (values +1);
		return sum;
		
	}
	static void incrementCount(HashMap<Integer,Integer> map, int key) {
		if(map.containsKey(key))
			map.put(key, map.get(key) +1);
		else
			map.put(key, 1);
	}
	
	static void decrementCount(HashMap<Integer,Integer> map, int key) {
		if(map.containsKey(key))
			map.put(key, map.get(key) -1);
		else
			map.put(key, 1);
	}
	
	public static void main(String[] args) {
		Factors p = new Factors();
		int n = 7200;
//		System.out.println("Factors "+p.afactors(n));
//		System.out.println("Sum of Factors " + p.sumOfFactors(n));
//		System.out.println("Number Of Factors " + p.numberOfFactors(n));
//		System.out.println("Sum of Even factors " + p.sumOfEvenFactors(n));
//		System.out.println("Number of Even factors " +p.numberOfEvenFactors(n));
//		System.out.println("Sum of odd factors " +p.sumOfOddFactors(n));
//		System.out.println("Number of odd factors " +p.numberOfOddFactors(n));
		
		System.out.println("Sum of factors divisible by " +p.sumOfFactorsDivisibleBy(n,25));
	}

}
