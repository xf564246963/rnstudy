import React, { Component, PropTypes } from 'react';
import { AppRegistry, AppState, StyleSheet, Text, View,Image,  DeviceEventEmitter, NativeModules, requireNativeComponent } from 'react-native';
//NativeModules, requireNativeComponent在不注册模块和组件的时候不要引入，否则客户端会报错

var Communicate=NativeModules.Communicate;
/* Communicate.addUser("jjz","123456",(msg)=>{
    alert(msg);
	communicate
  },(errorMsg)=>{
    alert(errorMsg)
}); */
setTimeout(()=>
	Communicate.login('jjz','123456').then((data)=>{
		alert(data);
	  },(message)=>{
		alert(message);
	})
,2000) 

class GradientColorView extends Component{
	constructor() {
		super();
		this._onChange = this._onChange.bind(this);
	}
	static propTypes = {
		...View.propTypes,
        startColor: PropTypes.string,
        endColor: PropTypes.string
	};
	_onChange(event: Event) {
		event.persist();
		setTimeout(()=>alert(JSON.stringify(event.nativeEvent.message)),4000);
	}
	render(){
		return (
			<NativeGradientColorView 
			 style={this.props.style}
			 startColor={this.props.startColor}
			 endColor={this.props.endColor}
			 onChange = {(event)=>{
				event.persist();
				setTimeout(()=>alert(event.nativeEvent.message+" without this"),4000);
			 }}/>
		)
	}
}

var NativeGradientColorView = requireNativeComponent('RCTGradientColorView', GradientColorView,  {
  nativeOnly: {onChange: true}
});


class UmsApp extends Component {
  componentDidMount() {
    //注册扫描监听
    DeviceEventEmitter.addListener('deviceready',this.listener);
	AppState.addEventListener('change', this.handleAppStateChange);
  }
  componentWillUnmount(){
	//一定要移出监听不然会调用多次监听回调  
    DeviceEventEmitter.removeListener('deviceready',this.listener);//移除扫描监听
	AppState.removeEventListener('change', this.handleAppStateChange);
  }
  listener(data){
	  alert(data);
  }
  handleAppStateChange(nextAppState){
	  setTimeout(()=>alert(nextAppState),4000);
  }
  render() {
    return (
      <View style={styles.container}>
        <Text style={styles.note}>
          welcome to react native PAGE
        </Text>
		<Image
			style={{width:100,height:100}}
			source={require('./img/ic.png')}
		  />
		<GradientColorView style={{width:80,height:80}} startColor="yellow" endColor="red"/>
      </View>
    );
  }
}

/* <GradientColorView style={{width:80,height:80}} startColor="yellow" endColor="red"/> */

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
  },
  note: {
    fontSize: 20
  }
});

AppRegistry.registerComponent('ums-react-native-app', () => UmsApp);
