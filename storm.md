### storm 

---------------------------------------------------------------------------------
* strom ��װ

1. ��װpython  
   ����Ϊ�˲��԰�װЧ�������ǽ����� storm-starter project������word coun���� 
   �õ���pythonд��multi-lang bolt��ʹ��python 2.7.6�� 
    
   ��װ·���ڣ�  
      C:\Python27\    
   python����װ��ʱ���Զ��������˻�������
   �����Ҳ��֪��ΪʲôҪ��װ�����Ͻ̳̰�װ����Ҳ��װ�ˣ�����û�в����Ƿ�����   

2. ��װ������zookeeper
   ��ַ�� [zookeeper] http://zookeeper.apache.org/doc/r3.3.6/zookeeperStarted.html#sc_Download "zookeeper"  
   ���ã�
      > cd zookeeper-3.3.6  
      > copy conf\zoo_sample.cfg conf\zoo.cfg  
      > .\bin\zkServer.cmd  
   
3. ��װstorm
   Storm��windows�ٷ��滹û���ͷţ������������һЩ�������İ�װ����   
   windows���а汾��[storm] https://dl.dropboxusercontent.com/s/iglqz73chkul1tu/storm-0.9.1-incubating-SNAPSHOT-12182013.zip  
   source�汾��[storm source] https://github.com/ptgoetz/storm/tree/windows-test  
  
4. ����storm��������  
      STORM_HOME ��D:\java\storm\storm-0.9.1  
      ��path�м��룺
        %STORM_HOME%\bin;C:\Python27;C:\Python27\Lib\site-packages\;C:\Python27\Scripts\

5. ����Storm
    storm������������������ɵģ������Ҫ�ֱ�����storm���������  
    ����Nimbus  
      > cd %STORM_HOME%  > storm nimbus   
    ����Supervisor  
      > cd %STORM_HOME%  > storm supervisor   
    ����Storm UI    
      > cd %STORM_HOME%  > storm ui   
      
    ��֤�����ɹ���  
      �������http://localhost:8080/ �ɿ���Storm���С�  
      ���⣺storm ui������jetty�е�һ���������ܿ���jettyҳ�棬ˢ��һ�ξͺ���  

6. ����work count  
    �����ĵ����ģ���֪����ʲô  
    
    ���ص�ַ��[work count] https://dl.dropboxusercontent.com/s/kc933u6vz2crqkb/storm-starter-0.0.1-SNAPSHOT-jar-with-dependencies.jar   
     
    �������jar�ڱ��أ�  
        > >storm jar storm-starter-0.0.1-SNAPSHOT-jar-with-dependencies.jar storm.starter.WordCountTopology WordCount -c nimbus.host=localhost
      
    ��֤��  
        ˢ�� Storm UIҳ�棬�ῴ�� ��WordCount�� topology��ʾ�г����㰴����ȷ�����������ݡ�


------------------------------------------------------------------------------------------------------------------
* storm java ���� 



  