import java.util.Scanner;
/**
  教师信息管理系统
  每个教师信息保存到对象中
  教师对象保存到数组中
*/
public class Tms {
	private	Teacher[] teas;//用于存储教师的信息
	private int index;	//用于记录数组中总共有几个教师

	//构造函数用于初始化对象中非静态属性
	public Tms(){
		teas = new Teacher[3];
		index = 0;
	}

	/**
	  保存
	  @param  教师对象
	*/
	public void save(Teacher tea){
		//数组的长度不足以保存教师了，数组的扩展
		if(index >= teas.length){
			Teacher[] demo = new Teacher[teas.length + 3];
			//数组拷贝，stus -> demo
			System.arraycopy(teas,0,demo,0,index);
			teas = demo;
		}
		teas[index++] = tea;
	}

	/**
	  查询所有的教师
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
		通过教师的id查找教师的信息
		 {{1001,terry,12},{1002,terry,12},null}
		 1002
	*/
	public Teacher queryById(long id){
		//获取该id所在数组中的索引
		int num = getIndexById(id);
		return num == -1?null:teas[num];
	}

	/**
		通过id获取编号号为该id的教师在数组中的位置
		 {{1001,terry,12},{1002,terry,12},null}
		id = 1002
		 1
	*/
	private int getIndexById(long id){
		int num = -1;//该教师找不到
		for(int i=0;i<index;i++){
			if(teas[i].getId() == id){
				num = i;
				break;
			}
		}
		return num;
	}
	
	public void menu(){
		System.out.println("********教师管理系统********");
		System.out.println("*1. 查询所有教师信息");
		System.out.println("*2. 录入教师信息");
		System.out.println("*3. 删除教师信息");
		System.out.println("*4. 查询单个教师信息");
		System.out.println("*5. 修改教师信息");
		System.out.println("*exit. 退出");
		System.out.println("*help. 帮助");
		System.out.println("****************************");
	}
	/**
		主方法
	*/
	public static void main(String[] args){
		Tms tms = new Tms();
		tms.menu();
		Scanner sc = new Scanner(System.in);
		while(true){
			System.out.print("请输入功能编号：");
			String option = sc.nextLine();
			//System.out.println("接收了："+option);
			switch(option){
				case "1":
					System.out.println("以下是教师的信息：");
					Teacher[] arr = tms.queryAll();
					for(int i=0;i<arr.length;i++){
						System.out.println(arr[i]);
					}
					System.out.println("总计 "+tms.index+" 个");
					break;
				case "2":
					while(true){
						System.out.println("请输入教师信息【id#name#age】或者输入【break】返回上一级目录");
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
						System.out.println("保存成功！");
					}
					
					break;
				case "3":
					break;
				case "4":
					while(true){
						System.out.println("请输入编号或者输入【break】返回上一级目录");
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
					System.out.println("bye bye,欢迎再次使用！");
					System.exit(0);
				case "help":
					tms.menu();
					break;
				default:
					System.out.println("不合法输入！");

			}
		}
	}
}