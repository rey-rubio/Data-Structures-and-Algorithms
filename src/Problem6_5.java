// Reynerio Rubio
// ID# 109899097
// ESE 554: Computational Models for Computer Engineers
// Chapter 6 Assignments

// Problem 6.5
// Implement a recursive approach to showing all the teams that
// can be created from a group (n things taken k at a time). Write
// the recursive showTeams() method and a main() method to prompt
// the user for the group size and the team size to provide arguments
// for showTeam(), which then displays all the possible combinations.

import java.util.Scanner;
import java.util.Vector;

class Teams {
    public Teams() { }

    public void showTeams(int n, int k)
    {

        Vector<Integer> v = new Vector<>();
        showTeams_helper(v, n, 1, k);
    }

    private void showTeams_helper(Vector<Integer> v, int n, int left, int k)
    {
        if (k == 0)
        {
            System.out.println(v);
            return;
        }
        for (int i = left; i <= n; i++)
        {
            v.add(i);
            showTeams_helper(v, n, i + 1, k - 1);
            v.remove(v.size() - 1);
        }
    }
}


public class Problem6_5
{

    public static void main(String args[])
    {
        while(true)
        {
            Scanner s = new Scanner(System.in);
            System.out.println("Please enter the GROUP size 'n':");
            int n = s.nextInt();

            System.out.println("Please enter the TEAM size 'k':");
            int k = s.nextInt();
            Teams myTeam = new Teams();
            myTeam.showTeams(n, k);
        }


    }

}
