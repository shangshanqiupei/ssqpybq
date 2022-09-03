import java.util.HashMap;
import java.util.Map;

public class OSManager {

    public Map<String, FileModel> totalFiles = new HashMap<String, FileModel>();
    //定义FAT表
    private int[] fat = new int[128];
    //创建根目录 使用fat表的第一项
    private FileModel root = new FileModel("root", 1);
    private FileModel nowCatalog = root;



    public OSManager() {
        // TODO Auto-generated consructor stub
        //将FAT表初始化全部为0，并将第一位设为根目录的空间
        for(int i=0; i<fat.length ; i++ ) {
            fat[i] = 0;
        }
        fat[1] = 255;//255表示磁盘块已占用
        fat[0] = 126; //纪录磁盘剩余块数
        root.setFather(root);
        totalFiles.put("root", root);
    }

    public int setFat(int size) {
        int[] startNum = new int[128];
        int i = 2; //纪录fat循环定位
        for(int j=0; j<size; i++) {
            if(fat[i] == 0) {
                startNum[j] = i; //纪录该文件所有磁盘块
                if(j>0) {
                    fat[startNum[j-1]] = i; //fat上一磁盘块指向下一磁盘块地址
                }
                j++;
            }
        }
        fat[i-1] = 255;
        return startNum[0]; //返回该文件起始块盘号
    }
    /*
     *
     * 该方法用于删除时释放FAT表的空间
     */
    public void delFat(int startNum) {
        int nextPoint = fat[startNum];
        int nowPoint = startNum;
        int count = 0;
        while(fat[nowPoint] != 0) {
            nextPoint = fat[nowPoint];
            if(nextPoint == 255) {
                fat[nowPoint] =0;
                count++;
                break;
            } else {
                fat[nowPoint] = 0;
                count++;
                nowPoint = nextPoint;
            }
        }
        fat[0] += count;
    }

    /*
     *
     * 以下为追加内容时修改fat表
     *
     */

    public void reAddFat(int startNum, int addSize) {
        int nowPoint = startNum;
        int nextPoint = fat[startNum];
        while(fat[nowPoint] != 255) {
            nowPoint = nextPoint;
            nextPoint = fat[nowPoint];
        }//找到该文件终结盘块

        for(int i=2, count = 0; count <addSize ; i++ ) {
            if(fat[i] == 0) {
                fat[nowPoint] = i;
                nowPoint = i;
                count++;
                fat[nowPoint] = 255;//作为当前文件终结盘块
            }
        }
    }

    public void createFile(String name, String type, int size) {

        if(fat[0] >= size) {	//判断磁盘剩余空间是否足够建立文件
            FileModel value = nowCatalog.subMap.get(name); //该目录下是否寻找同名目录或文件
            if(value != null) {  //判断该文件是否存在
                if(value.getAttr() == 3) {   //若存在同名目录 继续创建文件
                    int startNum = setFat(size);
                    FileModel file = new FileModel(name, type, startNum, size);
                    file.setFather(nowCatalog); //纪录上一层目录
                    nowCatalog.subMap.put(name, file); //在父目录添加该文件
                    totalFiles.put(file.getName(), file);
                    fat[0] -= size;
                    System.out.println("创建文件成功！");
                    showFile();
                } else if(value.getAttr() == 2) { //若同名文件已存在，创建失败
                    System.out.println("创建失败，该文件已存在！");
                    showFile();
                }
            } else if(value == null) { //若无同名文件或文件夹，继续创建文件
                int startNum = setFat(size);
                FileModel file = new FileModel(name, type, startNum, size);
                file.setFather(nowCatalog); //纪录上一层目录
                nowCatalog.subMap.put(name, file); //在父目录添加该文件
                totalFiles.put(file.getName(), file);
                fat[0] -= size;
                System.out.println("创建文件成功！");
                showFile();
            }
        } else {
            System.out.println("创建文件失败，磁盘空间不足！");
        }

    }

    public void createCatolog(String name) {

        if(fat[0] >= 1) { //判断磁盘空间是否足够创建文件夹

            FileModel value = nowCatalog.subMap.get(name); //判断该目录下是否存在同名目录或文件
            if(value != null) {
                if(value.getAttr() == 2) {
                    int startNum = setFat(1);
                    FileModel catalog = new FileModel(name, startNum);
                    catalog.setFather(nowCatalog); //纪录上一层目录
                    nowCatalog.subMap.put(name, catalog);
                    fat[0]--;
                    totalFiles.put(catalog.getName(), catalog);
                    System.out.println("创建目录成功！");
                    showFile();
                } else if(value.getAttr() == 3) {
                    System.out.println("创建目录失败，该目录已存在！");
                    showFile();
                }
            } else if(value == null) {
                int startNum = setFat(1);
                FileModel catalog = new FileModel(name, startNum);
                catalog.setFather(nowCatalog); //纪录上一层目录
                nowCatalog.subMap.put(name, catalog);
                fat[0]--;
                totalFiles.put(catalog.getName(), catalog);
                System.out.println("创建目录成功！");
                showFile();
            }
        } else {
            System.out.println("创建目录失败，磁盘空间不足！");
        }
    }


    public void showFile() {
        System.out.println("***************** < " + nowCatalog.getName() + " > *****************");

        if(!nowCatalog.subMap.isEmpty()) {
            for(FileModel value : nowCatalog.subMap.values()) {
                if(value.getAttr() == 3) { //目录文件
                    System.out.println("文件名 : " + value.getName());
                    System.out.println("操作类型 ： " + "文件夹");
                    System.out.println("起始盘块 ： " + value.getStartNum());
                    System.out.println("大小 : " + value.getSize());
                    System.out.println("<-------------------------------------->");
                }
                else if(value.getAttr() == 2) {
                    System.out.println("文件名 : " + value.getName() + "." + value.getType());
                    System.out.println("操作类型 ： " + "可读可写文件");
                    System.out.println("起始盘块 ： " + value.getStartNum());
                    System.out.println("大小 : " + value.getSize());
                    System.out.println("<-------------------------------------->");
                }
            }
        }
        for(int i =0; i<2; i++)
            System.out.println();
        System.out.println("磁盘剩余空间 ：" + fat[0] + "            " + "退出系统请输入exit");
        System.out.println();
    }
    public void deleteFile(String name) {

        FileModel value = nowCatalog.subMap.get(name);
        if(value == null) {
            System.out.println("删除失败，没有该文件或文件夹!");
        } else if(!value.subMap.isEmpty()) {
            System.out.println("删除失败，该文件夹内含有文件！");
        } else {
            nowCatalog.subMap.remove(name);
            delFat(value.getStartNum());
            if(value.getAttr() == 3) {
                System.out.println("文件夹 " + value.getName() + " 已成功删除");
                showFile();
            } else if(value.getAttr() == 2) {
                System.out.println("文件 " + value.getName() + "已成功删除");
                showFile();
            }
        }
    }

    /*
     *
     * 以下为文件或文件夹重命名方法
     *
     */

    public void reName(String name, String newName) {
        if(nowCatalog.subMap.containsKey(name)) {
            if(nowCatalog.subMap.containsKey(newName)) {
                System.out.println("重命名失败，同名文件已存在！");
                showFile();
            } else {
                //nowCatalog.subMap.get(name).setName(newName);
                FileModel value = nowCatalog.subMap.get(name);
                value.setName(newName);
                nowCatalog.subMap.remove(name);
                nowCatalog.subMap.put(newName, value);
                System.out.println("重命名成功！");
                System.out.println();
                showFile();
            }
        } else {
            System.out.println("重命名失败，没有该文件！");
            showFile();
        }
    }

    /*
     *
     * 以下为修改文件类型
     * 修改类型需要打开文件后才能操作
     */

    public void changeType(String name, String type) {

        nowCatalog = nowCatalog.getFather();
        if(nowCatalog.subMap.containsKey(name)) {
            FileModel value = nowCatalog.subMap.get(name);
            if(value.getAttr() == 2) {
                value.setType(type);
                nowCatalog.subMap.remove(name);
                nowCatalog.subMap.put(name, value);
                System.out.println("修改类型成功！");
                showFile();
            } else if(value.getAttr() == 3) {
                System.out.println("修改错误，文件夹无法修改类型！");
                openFile(value.getName());
            }
        } else {
            System.out.println("修改错误，请检查输入文件名是否正确！");
        }
    }

    /*
     * 以下为打开文件或文件夹方法
     *
     */

    public void openFile(String name) {
        if(nowCatalog.subMap.containsKey(name)) {
            FileModel value = nowCatalog.subMap.get(name);
            if(value.getAttr() == 2) {
                nowCatalog = value;
                System.out.println("文件已打开，文件大小为 : " + value.getSize());
            } else if(value.getAttr() == 3) {
                nowCatalog = value;
                System.out.println("文件夹已打开！");
                showFile();
            }
        } else {
            System.out.println("打开失败，文件不存在！");
        }
    }

    /*
     *
     * 以下为向文件追加内容方法
     * 追加内容需要打开文件后才能操作
     */

    public void reAdd(String name, int addSize) {

        if(fat[0] >= addSize) {
            nowCatalog = nowCatalog.getFather();
            if(nowCatalog.subMap.containsKey(name)) {
                FileModel value = nowCatalog.subMap.get(name);
                if(value.getAttr() == 2) {
                    value.setSize(value.getSize() + addSize);
                    reAddFat(value.getStartNum(), addSize);
                    System.out.println("追加内容成功！正在重新打开文件...");
                    openFile(name);
                } else{
                    System.out.println("追加内容失败，请确认文件名是否正确输入。");
                }
            } else {
                System.out.println("追加内容失败，请确认文件名是否正确输入！");
                showFile();
            }
        } else {
            System.out.println("追加内容失败，内存空间不足！");
        }
    }


    /*
     *
     * 以下为返回上一层目录
     *
     */

    public void backFile() {
        if(nowCatalog.getFather() == null) {
            System.out.println("该文件没有上级目录！");
        } else {
            nowCatalog = nowCatalog.getFather();
            showFile();
        }
    }

    /*
     * 以下根据绝对路径寻找文件
     *
     */

    public void searchFile(String[] roadName) {

        FileModel theCatalog = nowCatalog; //设置断点纪录当前目录

        if(totalFiles.containsKey(roadName[roadName.length-1])) { //检查所有文件中有无该文件

            nowCatalog = root; //返回根目录
            if(nowCatalog.getName().equals(roadName[0])) {	//判断输入路径的首目录是否root
                System.out.println("yes");
				/*for(String temp:roadName)
					System.out.println(temp);*/

                for(int i=1; i<roadName.length; i++) {
                    if(nowCatalog.subMap.containsKey(roadName[i])) {
						/*System.out.println(nowCatalog.getName());
						for(Entry<String, FileModel> s : nowCatalog.subMap.entrySet())
							System.out.println("键值对:" + s.getValue().getName());
							*/
                        nowCatalog = nowCatalog.subMap.get(roadName[i]); //一级一级往下查

                    } else {
                        System.out.println("找不到该路径下的文件或目录，请检查路径是否正确");
                        nowCatalog = theCatalog;
                        showFile();
                        break;
                    }
                }
                if(roadName.length>1){
                    nowCatalog = nowCatalog.getFather(); //返回文件上一级目录
                    showFile();
                }
            } else {
                nowCatalog = theCatalog;
                System.out.println("请输入正确的绝对路径！");
                showFile();
            }
        } else {
            System.out.println("该文件或目录不存在，请输入正确的绝对路径！");
            showFile();
        }
    }

    /*
     * 以下为打印FAT表内容
     *
     */
    public void showFAT() {

        for(int j=0; j<125; j+=5) {
            System.out.println("第几项 | " + j + "        " + (j+1) + "        " + (j+2) + "        "
                    + (j+3) + "        " + (j+4));
            System.out.println("内容    | " + fat[j] + "        " + fat[j+1] + "        " + fat[j+2]
                    + "        " + fat[j+3] + "        " + fat[j+4]);
            System.out.println();
        }
        int j = 125;
        System.out.println("第几项 | " + j + "        " + (j+1) + "        " + (j+2));
        System.out.println("内容    | " + fat[j] + "        " + fat[j+1] + "        " + fat[j+2]);
        System.out.println();
        showFile();
    }
}
