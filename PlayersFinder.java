package eg.edu.alexu.csd.datastructure.iceHockey.cs;

import java.awt.Point;

public class PlayersFinder implements IPlayersFinder
{

    private char [][] photoCh;
    private int r,c;
    private int[] x,y;
    private int pixelsI;

    private char[][] convertPhoto (String[] photo,int team)
    {
        r=photo.length;
        c= photo[0].length();
        photoCh=new char[r][c];
        for(int i=0; i<r; i++)
        {
            for(int j=0; j<c; j++)
            {
                if(Character.getNumericValue(photo[i].charAt(j))==team)
                {
                    photoCh[i][j]=photo[i].charAt(j);
                }
                else
                {
                    photoCh[i][j]='x';
                }
            }
        }
        return photoCh;
    }

    private int checkPixel(int i, int j)
    {
        if(photoCh[i][j] =='x')
        {
            return 0;
        }
        else
        {
            y[pixelsI]=i;
            x[pixelsI]=j;
            pixelsI++;
            photoCh[i][j]='x';
            if(j<c-1)   //right
            {
                checkPixel(i, j+1);
            }
            if(j>0)   //left
            {
                checkPixel(i, j-1);
            }
            if(i<r-1)   //down
            {
                checkPixel(i+1,j);
            }
            if(i>0)   //up
            {
                checkPixel(i-1,j);
            }
        }
        return 0;
    }
    private int min(int[] s,int size)
    {
        int min = s[0];
        for(int i=1; i<size; i++)
        {
            if(s[i]<min)
            {
                min=s[i];
            }
        }
        return min;
    }
    private int max(int[] s,int size)
    {
        int max = s[0];
        for(int i=1; i<size; i++)
        {
            if(s[i]>max)
            {
                max=s[i];
            }
        }
        return max;
    }

    private Point getCentre(int[] x, int[] y)
    {
        Point centre = new Point();
        centre.x=min(x,pixelsI)+max(x,pixelsI)+1;
        centre.y=min(y,pixelsI)+max(y,pixelsI)+1;
        return centre;
    }

    private Point[] sortPoints(Point[] points, int size)
    {
        Point[] sorted=new Point[size];
        Point pMin;
        for(int i=0; i<size; i++)
        {
            pMin=points[i];
            for(int j=i+1; j<size; j++)
            {
                if(points[j].x<pMin.x)
                {
                    pMin=points[j];
                    points[j]=points[i];
                    points[i]=pMin;
                }
                if(points[j].x==pMin.x)
                {
                    if(points[j].y<pMin.y)
                    {
                        pMin=points[j];
                        points[j]=points[i];
                        points[i]=pMin;
                    }

                }
            }
        }
        for(int i=0; i<size; i++)
        {
            sorted[i]=points[i];
        }
        return sorted;
    }
    @Override
    public Point[] findPlayers(String[] photo, int team, int threshold)
    {
        if(photo == null)
        {
        	System.out.println("empty photo");
            return null;
        }
        Point[] players = new Point[50];
        int pIndx=0;
        photoCh=this.convertPhoto(photo,team);
        for(int i=0; i<r; i++)
        {
            for(int j=0; j<c ; j++)
            {
                if(photoCh[i][j]!='x')
                {
                    pixelsI=0;
                    x=new int [50];
                    y=new int [50];
                    checkPixel(i, j);
                    if( pixelsI*4 >= threshold)
                    {
                        players[pIndx]=getCentre(x,y);
                        pIndx++;
                    }
                }
            }
        }
        if(pIndx==0)
        {
        	System.out.println("there is no players");
            return null;
        }
        players=sortPoints(players,pIndx);
        return players;
    }

}