package abc;
import java.util.*;
class items{
	static int temp=1;
	private int id;
	private String prod_name;
	private int price;
	private int quantity;
	items(String b,int c,int d)
	{
		this.id=temp++;
		this.prod_name=b;
		this.price=c;
		this.quantity=d;	
	}
	public String prodname()
	{
		return this.prod_name;
	}
	public int checkquantity()
	{
		return this.quantity;
	}
	public void add(int qua)
	{
		this.quantity+=qua;
	}
	public void delete()
	{
		this.quantity=0;
	}
	public void remove(int qua)
	{
		this.quantity-=qua;
	}
	public void initdisplay()
	{
		System.out.println(id+"\t"+prod_name+"    \t   "+price+"\t"+"\t");
		
	}
	public int pricecal()
	{
		return this.quantity*this.price;
	}
	public void orderdisplay()
	{
		if(this.quantity!=0)
		{
			System.out.println(id+"\t"+prod_name+"    \t   "+price+"\t "+quantity+"\t");
		}
	}
}

public class newcrud {
	public static void initialDisplay(items item[])
	{
		System.out.println("==========E-BAY==========");
		System.out.println("Displaying Products:");
		System.out.println("S.No\tProduct Name\t   Price");
		for (items i:item)
		{
			i.initdisplay();
		}
	}
	public static void orderedDisplay(items item[])
	{
		System.out.println("==========E-BAY==========");
		System.out.println("Displaying Ordered Products:");
		System.out.println("S.No\tProduct Name\t   Price\tQuantity");
		int totalPrice=0;
		for (items i:item)
		{
			i.orderdisplay();
		}
		for(items i:item)
		{
			totalPrice+=i.pricecal();
		}
		System.out.println("Total Price is: "+totalPrice);
	}
	public static void create(items item[],Scanner sc)
	{
		System.out.println("Enter which item no to add:");
		int itemno=sc.nextInt();
		System.out.println("Enter the quantity for "+item[itemno-1].prodname());
		int qua=sc.nextInt();
		item[itemno-1].add(qua);
		
	}
	public static void update (items item[],Scanner sc )
	{
		System.out.println("Enter which item no to update:");
		int itemno=sc.nextInt();
		System.out.println("Do you wish to add or remove: 1.ADD 2.REMOVE");
		int ans=sc.nextInt();
		if(ans==1)
		{
			System.out.println("Enter the quantity to add for "+item[itemno-1].prodname());
			int qua=sc.nextInt();
			item[itemno-1].add(qua);
		}
		else
		{
			System.out.println("Enter the quantity to remove from "+item[itemno-1].prodname());
			int qua=sc.nextInt();
			int alread=item[itemno-1].checkquantity();
			
			if(alread-qua<0)
				System.out.println("Sorry Not Possible!!..Select valid amount of quantity");
			else
				item[itemno-1].remove(qua);
		}
			
	}
	public static void delete(items item[],Scanner sc)
	{
		System.out.println("Enter which item no to delete:");
		int itemno=sc.nextInt();
		System.out.println("Do you want to delete "+item[itemno-1].prodname()+"? \t1.YES \t2.NO");
		int wish=sc.nextInt();
		if(wish==1)
		{
			item[itemno-1].delete();
		}
		System.out.println(item[itemno-1].prodname()+"  Deleted Successfully");
	}
	public static int randomotp()
	{
		Random rand = new Random();
		int max = 9999, min = 1111;
		int random = rand.nextInt(max - min + 1) + min;
		return random;
	}
	public static void payment(Scanner sc)
	{	
		int otp = 0,tries = 0;
		tries = 1;
		while(tries<3)
		{
			otp=randomotp();
			System.out.println("OTP: \t"+otp);
			System.out.println("Enter OTP for payment:");
			int userotp=sc.nextInt();
			if(otp==userotp)
			{
				System.out.println("Payment Successfull!!.");
				System.exit(0);
			}
			else
			{
				System.out.println("Wrong OTP!!  " +"Retries Left:"+ (2-tries));
				tries++;
			}	
		}	
		if(tries==3)
		{
			System.out.println("Sorry !!We cannot process your payment!.Thank You!");
			System.exit(0);
		}
	}
	public static void main(String[] args) {
		
		items[] item= {
				new items("Mobile",20000,0),
				new items("Laptop",120000,0),
				new items("Earphones",2000,0),
				new items("Mouse",200,0),
				new items("Keyboard",600,0)
		};
		initialDisplay(item);
		Scanner sc=new Scanner(System.in);
		int ch=1;
		while(ch!=5)
		{
			System.out.println("\nChoose which action you want to do:");
			System.out.println("1.ADD \n2.DELETE \n3.UPDATE \n4.PAYMENT \n5.EXIT");
			ch=sc.nextInt();
			switch(ch)
			{
				case 1:
					int cflag=1;
					while(cflag!=2)
					{
						create(item,sc);
						orderedDisplay(item);
						System.out.println("\nDo you wish to add more? \t 1.YES 2.NO :");
						cflag=sc.nextInt();
						if(cflag==2)
						{
							break;
						}
					}						
					break;
				case 2:
					int dflag=1;
					while(dflag!=2)
					{
						delete(item,sc);
						orderedDisplay(item);
						System.out.println("\nDo you wish to delete more? \t 1.YES 2.NO");
						dflag=sc.nextInt();
						if(dflag==2)
							break;	
					}
					break;
				case 3:
					int uflag=1;
					while(uflag!=2)
					{
						update(item,sc);
						orderedDisplay(item);
						System.out.println("\nDo you wish to update more? \t 1.YES 2.NO");
						uflag=sc.nextInt();
						if(uflag==2)
							break;
					}
					break;
				case 4:
					orderedDisplay(item);
					System.out.println("Proceed to payment??\t 1.YES 2.NO");
					int proceed=sc.nextInt();
					if(proceed==1)
						payment(sc);
					break;
				case 5:
					break;
				default:
					System.out.println("Invalid Choice!!");
			}
		}
	}

}
