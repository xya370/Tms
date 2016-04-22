import java.util.Scanner;
/**
  ��ʦ��Ϣ����ϵͳ
  ÿ����ʦ��Ϣ���浽������
  ��ʦ���󱣴浽������
*/
public class Tms {
	private	Teacher[] teas;//���ڴ洢��ʦ����Ϣ
	private int index;	//���ڼ�¼�������ܹ��м�����ʦ

	//���캯�����ڳ�ʼ�������зǾ�̬����
	public Tms(){
		teas = new Teacher[3];
		index = 0;
	}

	/**
	  ����
	  @param  ��ʦ����
	*/
	public void save(Teacher tea){
		//����ĳ��Ȳ����Ա����ʦ�ˣ��������չ
		if(index >= teas.length){
			Teacher[] demo = new Teacher[teas.length + 3];
			//���鿽����stus -> demo
			System.arraycopy(teas,0,demo,0,index);
			teas = demo;
		}
		teas[index++] = tea;
	}

	/**
	  ��ѯ���еĽ�ʦ
	  teas= new Teacher[3];
	  {{1001,terry,12},{1002,terry,12},null}
	  {{1001,terry,12},{1002,terry,12}}
	  index = 1
	*/
	public Teacher[] queryAll(){
		Teacher[] demo = new Teacher[index];



		System.arraycopy(teas,0,demo,0,index);
		return demo;
	}

	/**
		ͨ����ʦ��id���ҽ�ʦ����Ϣ
		 {{1001,terry,12},{1002,terry,12},null}
		 1002
	*/
	public Teacher queryById(long id){
		//��ȡ��id���������е�����
		int num = getIndexById(id);
		return num == -1?null:teas[num];
	}

	/**
		ͨ��id��ȡ��ź�Ϊ��id�Ľ�ʦ�������е�λ��
		 {{1001,terry,12},{1002,terry,12},null}
		id = 1002
		 1
	*/
	private int getIndexById(long id){
		int num = -1;//�ý�ʦ�Ҳ���
		for(int i=0;i<index;i++){
			if(teas[i].getId() == id){
				num = i;
				break;
			}
		}
		return num;
	}
	
	public void menu(){
		System.out.println("********��ʦ����ϵͳ********");
		System.out.println("*1. ��ѯ���н�ʦ��Ϣ");
		System.out.println("*2. ¼���ʦ��Ϣ");
		System.out.println("*3. ɾ����ʦ��Ϣ");
		System.out.println("*4. ��ѯ������ʦ��Ϣ");
		System.out.println("*5. �޸Ľ�ʦ��Ϣ");
		System.out.println("*exit. �˳�");
		System.out.println("*help. ����");
		System.out.println("****************************");
	}
	/**
		������
	*/
	public static void main(String[] args){
		Tms tms = new Tms();
		tms.menu();
		Scanner sc = new Scanner(System.in);
		while(true){
			System.out.print("�����빦�ܱ�ţ�");
			String option = sc.nextLine();
			//System.out.println("�����ˣ�"+option);
			switch(option){
				case "1":
					System.out.println("�����ǽ�ʦ����Ϣ��");
					Teacher[] arr = tms.queryAll();
					for(int i=0;i<arr.length;i++){
						System.out.println(arr[i]);
					}
					System.out.println("�ܼ� "+tms.index+" ��");
					break;
				case "2":
					while(true){
						System.out.println("�������ʦ��Ϣ��id#name#age���������롾break��������һ��Ŀ¼");
						String teaStr = sc.nextLine();
						if(teaStr.equals("break")){
							break;
						}
						//1001#terry#12
						String[] teaArr = teaStr.split("#");
						long id = Long.parseLong(teaArr[0]);
						String name = teaArr[1];
						int age = Integer.parseInt(teaArr[2]);
						Teacher tea = new Teacher(id,name,age);
						tms.save(tea);
						System.out.println("����ɹ���");
					}
					
					break;
				case "3":
					break;
				case "4":
					while(true){
						System.out.println("�������Ż������롾break��������һ��Ŀ¼");
						String idStr = sc.nextLine();
						if(idStr.equals("break")){
							break;
						}
						//1001#terry#12
						long id = Long.parseLong(idStr);
						Teacher tea = tms.queryById(id);
						System.out.println(tea==null?"sorry,not found!":tea);
					}
					break;
				case "5":
					break;
				case "exit":
					System.out.println("bye bye,��ӭ�ٴ�ʹ�ã�");
					System.exit(0);
				case "help":
					tms.menu();
					break;
				default:
					System.out.println("���Ϸ����룡");

			}
		}
	}
}