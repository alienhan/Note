
##git##

����http://www.git-scm.com/
------------------------------------------------------------------------------------------
1. github 
username :tonghuajianghan
email :tonghuajianghan@gmail.com
password : �ٶ�

------------------------------------------------------------------------------------------
2. ����git�ֿ�

����û��������룺
git config --global user.name "jh" ��github��¼����
git config --global user.email "tonghuajianghan@gmail.com"��github��½���䣩

��ʼ��git���ش洢�⣺
����һ���ļ�->cd ����ļ��У�����cd E��note��
->git init����ʼ������git��
��
ͼ�λ�����Git GUI����һ���°汾��
��
��¡һ�����ش洢��
cd f:
$ git clone git://github.com/schacon/grit.git


Eclipse Git �����
�Ҽ���Ŀ��->Team->share project ->Git->
->ѡ���ļ�λ�ã��洢�⣩
->ѡ��汾������Ŀ����λ�ã�

------------------------------------------------------------------------------------------
3. git���زֿ���github����

ssh ����
1).����ssh��Կ��Administrator/.ssh���ļ�λ�ã�Ĭ�����ɣ�
�����ʽ�Ĺ���ÿ��git�汾��Щ��Ĳ�ͬ��

��ʽ1��	$ssh keygen -t rsa -C "tonghuajianghan@gmail.com"
���ܱ���
	ssh: Could not resolve hostname keygen: Name or service not known

��ʽ2�� $ssh-keygen -t rsa -C "tonghuajianghan@gmail.com"


���� id_rsa �� id_rsa.pub ������Կ�ļ�
�����ֻ���ʾ�������������루����������push�ļ���ʱ��Ҫ��������룬
������github�����ߵ����룩

2).������ SSH key �� github����ȥ
account setting������

3).�½����ذ汾��
����һ���ļ������ļ���
git init �ʹ���һ��git���ذ汾��
����һ��.git�ļ�
sts�һ���Ŀ����>Team->share Patch
->ѡ�иղ����õ�git���ذ汾�����

4).�½�githubԶ�̰汾�⣨��github��վ���½���
  4.1new repository ->create Repository->���
  4.2Create a new repository on the command line

touch README.md
git init
git add README.md
git commit -m "first commit"
git remote add origin git@github.com:lizhenyu/helloworld.git
git push -u origin master
Push an existing repository from the command line


5).���ӱ��ذ汾����Զ�̰汾��
origin��Ĭ������Զ�̰汾��
$git remote -v �г�Զ�̰汾����ϸ����
Ҫ���һ���µ�Զ�ֿ̲�,����ָ��һ���򵥵�����,
�Ա㽫������,���� git remote add [shortname] [url]:
��1��
git remote add origin https://github.com/lizhenyu/helloworld.git
git push -u origin master				   
��2��
$git remote add origintestgit0001 git@github.com:tonghuajianghan/firstdemo.git
                (���ڱ��ر���ʶ)��ȡ��firstdemo.git

$ git push -u origin master
Counting objects: 19, done.
Delta compression using up to 4 threads.
Compressing objects: 100% (19/19), done.
Writing objects: 100% (19/19), 13.73 KiB, done.
Total 23 (delta 6), reused 0 (delta 0)
To git@github.com:tonghuajianghan/firstdemo.git
 * [new branch]      master -> master
Branch master set up to track remote branch master from origin.
�ѱ��ؿ���������͵�Զ�̣���git push���ʵ�����ǰѵ�ǰ��֧master���͵�Զ�̡�
����Զ�̿��ǿյģ����ǵ�һ������master��֧ʱ��
������-u������Git������ѱ��ص�master��֧�������͵�Զ���µ�master��֧��
����ѱ��ص�master��֧��Զ�̵�master��֧����������

6).�ٴ��ύԶ�̰汾��
$ git push origin master	
���Ժ�����ͻ�����ȡʱ�Ϳ��Լ����
ֻҪ���������ύ���Ϳ���ͨ�����

7).
��ĳ��Զ�������ĸ��£�ȫ��ȡ�ر��ء�
$ git fetch <Զ��������>
Ĭ������£�git fetchȡ�����з�֧��branch���ĸ��¡�
���ֻ��ȡ���ض���֧�ĸ��£�����ָ����֧����
$ git fetch <Զ��������> <��֧��>
���磬ȡ��origin������master��֧��
$ git fetch origin master
��ȡ�صĸ��£��ڱ���������Ҫ��"Զ��������/��֧��"����ʽ��ȡ��
����origin������master����Ҫ��origin/master��ȡ��

8).��¡Զ�̷������ֿ⵽���أ�
$git clone git@github.com:tonghuajianghan/firstdemo.git

9).git �ύʱ������ĳЩ�ļ�


------------------------------------------------------------------------------------------
git�������

ֻ�������ύ���û��������䣨������ʾ��
$git config --global user.name ""
$git config --global user.email ""

��ʾconfig����
$git config --list

updates were rejected because the tip
Զ�̰汾����ڱ��ذ汾��
Ӧ����pull

------------------------------------------------------------------------------------------
git branch

git branch �����������г������Ѿ����ڵķ�֧��
�����ڵ�ǰ��֧��ǰ��ӡ�*���ű��

git branch -r �г�Զ�̷�֧

git branch -a �г����ط�֧��Զ�̷�֧

git branch ����һ���µı��ط�֧����Ҫע�⣬
�˴�ֻ�Ǵ�����֧�������з�֧�л�
example: git branch mybranch

$ git checkout -b iss53 �½����л�

git branch -m | -M oldbranch newbranc
��������֧�����newbranch���ַ�֧�Ѿ����ڣ�
����Ҫʹ��-Mǿ��������������ʹ��-m������������

git branch -d | -D branchname 
ɾ��branchname��֧

git branch -d -r branchname 
ɾ��Զ��branchname��֧

Ҫ�л���������֧������ִ�� git checkout ����
$ git checkout testing
������ļ�ʱ��û��git add ��git������ļ����м���
�������л���֧ʱ���ļ������ܵ�Ӱ��

��ͬ��֧��ʼ��ʱ�����̳�master���ļ�

git merge �ϲ�


------------------------------------------------------------------------------------------
git ɾ���ļ�

�ڱ� git �����Ŀ¼��ɾ���ļ�ʱ������ѡ���������ַ�ʽ����¼ɾ������:
һ��rm + git commit -am "abc"
����git rm + git commit -m "abc"
���⣬git add . ���ܼ�¼��ӡ��Ķ��Ķ�����ɾ���Ķ����迿 git rm ����ɡ�

���rm ɾ�����ļ��Ǵ��� not staged ״̬�ģ�
Ҳ����һ�ֽ��� ��δ�Ķ��� �� �����ύ���� ֮���״̬��


------------------------------------------------------------------------------------------
git .gitignore
	ע��ո񣡣���

	�� .git ��ͬ�ļ������½�.gitignore�ļ�

	#ע��
	/build/ ���Ը�Ŀ¼��build�ļ����µ������ļ�

------------------------------------------------------------------------------------------
git �ع� reset

���ػع�

git reset --soft | --mixed | --hard
					Ĭ��

--mixed
�ᱣ��Դ��,ֻ�ǽ�git commit��index ��Ϣ���˵���ĳ���汾.

--soft
����Դ��,ֻ���˵�commit ��Ϣ��ĳ���汾.���漰index�Ļ���,�������Ҫ�ύ,ֱ��commit����.

--hard
Դ��Ҳ����˵�ĳ���汾,commit��index ���ػ��˵�ĳ���汾.(ע��,���ַ�ʽ�Ǹı䱾�ش���ֿ�Դ��)

----------------------------------------------------------------------------------------------
git commit -a -m 'added new benchmarks' 

����ʹ���ݴ�����
����ʹ���ݴ�����ķ�ʽ���Ծ���׼��Ҫ�ύ��ϸ�ڣ�
����ʱ����ô�����Է����� Git �ṩ��һ������ʹ���ݴ�����ķ�ʽ��
ֻҪ���ύ��ʱ�򣬸� git commit ���� -a ѡ�
Git �ͻ��Զ��������Ѿ����ٹ����ļ��ݴ�����һ���ύ���Ӷ����� git add ����

----------------------------------------------------------------------------------------------
EGit myeclipse 

egit http://archive.eclipse.org/egit/updates-3.0
myeclipse 2013