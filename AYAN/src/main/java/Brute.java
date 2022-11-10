package TD;

public class Brute {
	public static void main(String[] args) {
		int n=4;
		int[] P= {1,2,3,4};
		boolean r=false;
		int p1,p2,p3,p4;
		int s1 = 0,s2=0;
		for(p1=0;p1<=1;p1++) {
			for(p2=0;p2<=1;p2++) {
				for(p3=0;p3<=1;p3++) {
					for(p4=0;p4<=1;p4++) {
						if(p1*P[0]+p2*P[1]+p3*P[2]+p4*P[3]==(1-p1)*P[0]+(1-p2)*P[1]+(1-p3)*P[2]+(1-p4)*P[3]) {
							s1=p1*P[0]+p2*P[1]+p3*P[2]+p4*P[3];
							s2=(1-p1)*P[0]+(1-p2)*P[1]+(1-p3)*P[2]+(1-p4)*P[3];
							r=true;
						}
					}
				}
			}
		}
System.out.println("s1= "+s1 +"  s2= "+s2);
System.out.println(Integer.toBinaryString(5));
	}

}
