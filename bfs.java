import java.util.*;
class bfs
{
    int num;
    LinkedList ll;
    char state;
    int level;
    int parent;
    int[] que;
    int top;
    bfs(int n)
    {
        num=n;
        que=new int[n];
        top=-1;
    }
    bfs()
    {
        ll=new LinkedList();
        state='w';
        level=-1;
        parent=-1;
    }
    void addEdge(int b)
    {
        ll.add(new Integer(b));
    }
    void push(int x)
    {
        for(int i=top;i>=0;i--)
        que[i+1]=que[i];
        que[0]=x;
        top++;
    }
    int pop()
    {
        Integer x=que[0];
        for(int i=0;i<top;i++)
        que[i]=que[i+1];
        top--;
        return x;
    }
    public static void main(String args[])
    {
        Scanner in=new Scanner(System.in);
        System.out.println("Enter the number of nodes in the graph");
        int n=in.nextInt();
        bfs[] obj=new bfs[n];
        for(int i=0;i<n;i++)
        obj[i]=new bfs();
        System.out.println("Enter number of edges");
        int k=in.nextInt();
        System.out.println("Enter the edges eg: node a node b edge weight");
        for(int i=0;i<k;i++)
        {
            int a=in.nextInt();
            int b=in.nextInt();
            obj[a-1].addEdge(b);
            obj[b-1].addEdge(a);
        }
        bfs obj1=new bfs(n);
        System.out.println("Enter the starting node");
        int s=in.nextInt();
        obj1.push(s);
        obj[s-1].state='g';
        obj[s-1].level=0;
        while(obj1.top!=-1)
        {
            int node=obj1.pop();
            obj[node-1].state='b';
            for(int i=0;i<obj[node-1].ll.size();i++)
            {
                int temp=(int)obj[node-1].ll.get(i);
                if(obj[temp-1].state=='w'||obj[temp-1].level>((obj[node-1].level)+1))
                {
                    obj1.push(temp);
                    obj[temp-1].state='g';
                    obj[temp-1].level=(obj[node-1].level)+1;
                    obj[temp-1].parent=node;
                }
            }
        }
        for(int i=0;i<n;i++)
        System.out.println("Minimum distance of node "+(i+1)+" from node "+s+"  = "+obj[i].level);
    }
}