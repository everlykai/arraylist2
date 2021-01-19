import java.util.*;

public class arrListAssignment {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);

        System.out.print("1 = sieve \n2 = Goldbach \n3 = bigInt \nPLEASE ENTER NUMBER: ");
        do{
            int mode = Integer.parseInt(scn.nextLine());
            System.out.println();
            if (mode == 1) {
                System.out.print("Enter argument for the \"Sieve of Eratosthenes\": ");
                int x = Integer.parseInt(scn.nextLine());
                System.out.println(sieve(x));
            }
            else if (mode == 2) {
                System.out.print("Enter argument for the \"Goldbach Conjecture\": ");
                int y = Integer.parseInt(scn.nextLine());
                System.out.println(goldBach(y));
            }
            else if (mode == 3) {
                System.out.print("enter value 1 for bigInts: ");
                long val1 = Long.parseLong(scn.nextLine());
                System.out.print("value 2: ");
                long val2 = Long.parseLong(scn.nextLine());
                System.out.print(val1+" + "+val2+" = ");
                System.out.println(addBigInts(toArray(val1), toArray(val2)));
            }
            else {
                System.out.print("invalid! try again: ");
                continue;
            }
            System.out.print("\nPLEASE ENTER NUMBER: ");
        }
        while(scn.hasNext());
    }

    private static ArrayList<Integer> toArray(long num) {
        ArrayList<Integer> array = new ArrayList<>();
        do {
            array.add(0,(int)(num%10L));
            num /= 10;
        } while (num > 0);
        return array;
    }

    private static ArrayList<Integer> addBigInts(ArrayList<Integer> list1, ArrayList<Integer> list2) {
        ArrayList<Integer> sum = new ArrayList<>();
        boolean over10 = false;
        int digit;
        int x = list1.size()-1;
        int y = list2.size()-1;
        // add from last index
        // carry over if needed

        for (int i = 0; i < list1.size() || i < list2.size(); i++) {
            if(over10) {
                digit = 1;
                over10 = false;
            }
            else digit = 0;

            if(x>=0) digit += list1.get(x);
            if(y>=0) digit += list2.get(y);

            if (digit>=10) {
                over10 = true;
                digit%=10;
            }

            sum.add(0,digit);
            x--;
            y--;
        }
        if(over10) sum.add(0,1);
        return sum;
    }

    private static ArrayList<Integer> sieve(int n) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i=2; i<=n; i++) { //fills the array
            list.add(i);
        }

        for (int i=0; i<list.size(); i++) {
            for (int j=i; j<list.size(); j++) {
                if(list.get(i) < list.get(j) && list.get(j)%list.get(i) == 0)
                    list.remove(j); //no need to
            }
        }

        return list;
    }

    private static String goldBach(int n) {
        ArrayList<Integer> list = sieve(n);
        int i = 0;

        for (int j=list.size()-1; j>=0;) {
            if(list.get(i) + list.get(j) == n)
                return (list.get(i)+" + "+list.get(j)+" = "+n);
            else if(list.get(i) + list.get(j) > n) {
                j--;
                continue;
            }
            i++;
        }

        return "error"; //if n is odd
    }

}