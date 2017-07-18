import React, { Component } from 'react';
import { AppRegistry, StyleSheet, Text, View, NativeModules, DeviceEventEmitter } from 'react-native';

var Communicate=NativeModules.Communicate;
Communicate.addUser("jjz","123456",(msg)=>{
    alert(msg);
  },(errorMsg)=>{
    alert(errorMsg)
});
setTimeout(()=>
	Communicate.login('jjz','123456').then((data)=>{
		alert(data);
	  },(code,message)=>{
		alert(message);
	})
,2000)


class UmsApp extends Component {
  componentDidMount() {
    //注册扫描监听
    DeviceEventEmitter.addListener('deviceready',this.listener);
  }
  componentWillUnmount(){
	//一定要移出监听不然会调用多次监听回调  
    DeviceEventEmitter.removeListener('deviceready',this.listener);//移除扫描监听
  }
  listener(data){
	  alert(data);
  }
  render() {
    return (
      <View style={styles.container}>
        <Text style={styles.note}>
          welcome to test react native
        </Text>
      </View>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
  },
  note: {
    fontSize: 20,
	color:'red'
  }
});

AppRegistry.registerComponent('ums-react-native-app', () => UmsApp);
