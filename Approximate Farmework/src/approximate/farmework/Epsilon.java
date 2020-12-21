/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package approximate.farmework;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Mehran
 */
public class Epsilon {
    
    /**
     e is the threshold for probabilistic bisimulation
     */
    public static double  e= 0.05;
    List<int[]> ascc = new ArrayList<>();
    

    
    List<int[]> ASCC(List<int[]> C, List<Object[]> tra)
    {
        List<int[]> bis = new ArrayList<>();
        int [] temp;
        //for each component of the model we apply e-approximate bisimulation
        for(int i = 0; i < C.size(); i++) 
        {
            temp=C.get(i);
            bis=eBisimulation(temp,tra);
            temp=Reduce(temp,bis);
            ascc.add(temp);
            
        }
        return ascc;
    }

    private List<int[]> eBisimulation(int[] a, List<Object[]> tra) //determines bisimilar states as a number of pairs
    {
        Object [] tmpo=new Object[4];
        List<Object[]> tra1 = new ArrayList<>();//transition list of the first state
        List<Object[]> tra2 = new ArrayList<>();//transition list of the second state
        List<int[]> bisim = new ArrayList<>();//list of bisimilar pairs
        int [] pair=new int[2];
        int counter=0;

        for (int i = 0; i <a.length & a[i+1]!=0; i++)//travers for the first state
        {
          for(int j = 0; j < tra.size(); j++)
          {
             if(tra.get(j)[0].equals(a[i]))
             {
                tmpo[0] = tra.get(j)[0];
                tmpo[1] = tra.get(j)[1];
                tmpo[2] = tra.get(j)[2];
                tmpo[3] = tra.get(j)[3];
                tra1.add(tmpo);
             }
          }
             if(!tra1.isEmpty())
             for (int k = i; k <a.length & a[k+1]!=0; k++)//travers for comparing state
             {
                 for(int l = 0; l < tra.size(); l++)
                 {
                    if(tra.get(l)[0].equals(a[k]))
                    {
                        tmpo[0] = tra.get(l)[0];
                        tmpo[1] = tra.get(l)[1];
                        tmpo[2] = tra.get(l)[2];
                        tmpo[3] = tra.get(l)[3];
                        tra2.add(tmpo);
                    }
                }
                 
                 //comparing the first state with the second one
                 
                 if(!tra1.isEmpty()&!tra2.isEmpty())
                 for(int m = 0; m < tra1.size(); m++)
                     for(int n = 0; n < tra2.size(); n++)
                         if(tra1.get(m)[1].equals(tra2.get(n)[1])&tra1.get(m)[3].equals(tra2.get(n)[3])&(Math.abs(Double.parseDouble(tra1.get(m)[2].toString())-Double.parseDouble(tra2.get(n)[2].toString()))<=e))
                             counter++;
                         
                         
                 if(counter==(tra1.size()*tra2.size()))
                 {
                     pair[0]=i;
                     pair[1]=k;
                     bisim.add(pair.clone());
                     System.out.println("The pair "+i+" and "+k+" are bisimilar");
                 }
                 
                 tra2.removeAll(tra2);
                 
            }
             
             tra1.removeAll(tra1);
             
        }
        
        return bisim;
        
    }

    private int[] Reduce(int[] scc, List<int[]> bis) //gets the an array of states of an scc and bisimilar states, and reduces the bisimilar states
    {
        
        int [] arr= new int[10];//reduced list
        int [] rem= new int[10];//removal list
        int k=0;
        int l=0;
        boolean in=false;
        
        //Arrays.binarySearch(arr, k)
        
        for(int i = 0; i < scc.length; i++)
        {
            in=true;
            for(int j=0; j<bis.size();j++)
            {
                if(scc[i]==bis.get(j)[1])
                    if(Arrays.binarySearch(rem, bis.get(j)[0])==-1)//not previously removed
                    {
                        rem[l]=scc[i];
                        in=false;
                        break;
                    }
            }
            if(in==true)
            {
                arr[k]=scc[i];
                k++;
            }
        }
            
        
        
        
        return arr;
    }
    
    
}
