package com.jks.demo.utils;

import com.offbytwo.jenkins.JenkinsServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * 连接 Jenkins
 */
public class JenkinsConnect {
	private static final Logger logger = LoggerFactory.getLogger(JenkinsConnect.class);
 
    private JenkinsConnect(){}
 
    // 连接 Jenkins 需要设置的信息
//    static final String JENKINS_URL = "http://10.192.43.11:44106";
    static final String JENKINS_USERNAME = "admin";
    static final String JENKINS_PASSWORD = "123456";
 
    /**
     * Http 客户端工具
     *
     * 如果有些 API 该Jar工具包未提供，可以用此Http客户端操作远程接口，执行命令
     * @return
     */
    public static MyJenkinsHttpClient getClient(String JENKINS_URL){
    	MyJenkinsHttpClient jenkinsHttpClient = null;
        try {
            jenkinsHttpClient = new MyJenkinsHttpClient(new URI(JENKINS_URL), JENKINS_USERNAME, JENKINS_PASSWORD);
        } catch (URISyntaxException e) {
        	logger.error(e.getMessage(),e);
        }
        return jenkinsHttpClient;
    }
 
    /**
     * 连接 Jenkins
     */
    public static JenkinsServer connection(String JENKINS_URL) {
        JenkinsServer jenkinsServer = null;
        try {
            jenkinsServer = new JenkinsServer(new URI(JENKINS_URL), JENKINS_USERNAME, JENKINS_PASSWORD);
        } catch (URISyntaxException e) {
        	logger.error(e.getMessage(),e);
        }
        return jenkinsServer;
    }
    
//    public static void main(String[] args) throws Exception {
////    	JenkinsServer jenkinsServer = JenkinsConnect.connection();
////    	jenkinsServer.
////    	JenkinsHttpClient jenkinsHttpClient = JenkinsConnect.getClient();
////    	System.out.println("11111111");
////    	System.out.println(jenkinsHttpClient.get("configureClouds"));
//    	String JENKINS_URL = "http://10.192.43.11:44106";
//    	MyJenkinsHttpClient ss = new MyJenkinsHttpClient(new URI(JENKINS_URL), JENKINS_USERNAME, JENKINS_PASSWORD);
//    	
//    	String info = "{'cloud': [{'name': 'kubernetes1', 'serverUrl': '10.192.43.11:6443', 'useJenkinsProxy': false, 'serverCertificate': '', 'skipTlsVerify': false, 'namespace': 'asdasd', 'includeUser': 'false', 'credentialsId': '', 'webSocket': false, 'directConnection': false, 'jenkinsUrl': 'http://10.192.43.11:29630', 'jenkinsTunnel': '10.110.89.37:50000', 'connectTimeout': '60', 'readTimeout': '60', 'containerCapStr': '10', 'maxRequestsPerHostStr': '32', 'waitForPodSec': '600', 'retentionTimeout': '5', 'addMasterProxyEnvVars': false, 'usageRestricted': false, 'defaultsProviderTemplate': '', 'stapler-class': 'org.csanchez.jenkins.plugins.kubernetes.KubernetesCloud', '$class': 'org.csanchez.jenkins.plugins.kubernetes.KubernetesCloud'}], 'core:apply': 'true'}";
//    	Map<String, String> map = new HashMap<String, String>();
//    	map.put("json", info);
////    	ss.post_form("configureClouds/configure", map, true);
//    	System.out.println("--------------------------------------");
//    	System.out.println(ss.get("http://10.192.43.11:44106/configureClouds"));
//    	System.out.println("--------------------------------------");
//    	
////    	String sss =ss.get("configureClouds");
////    	System.out.println(sss);
//	}
}
