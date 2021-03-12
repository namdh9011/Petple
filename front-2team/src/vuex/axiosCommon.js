import axios from 'axios';
import store from './store';
import { mapMutations } from 'vuex';

export default axios.create({
  // https://cors-anywhere.herokuapp.com/
  // baseURL: 'http://70.12.246.112:8080', //유리
  // baseURL: 'http://70.12.247.104:9090', //동해형
  baseURL: 'http://i02a202.p.ssafy.io:8080/petple', //aws
  // bodys:{
  //   'abc' : 'abc'
  // },
  headers: {
    'Content-type' : 'application/json',
    // 'Authurization' : String(sessionStorage.getItem('user_token')),
    // Authorization :  String(sessionStorage.getItem('user_token')),
    'Authorization' :  String(sessionStorage.getItem('user_token')),
    // 'Authorization' : 'eyJ0eXAiOiJKV1QiLCJyZWdEYXRlIjoxNTgxNDE0Mzc0NDg3LCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0IiwiMSI6eyJ1bnVtIjoiMSIsIm5hbWUiOiJ0ZXN0IiwiZW1haWwiOiJ0ZXN0QHRlc3QuY29tIiwicGFzc3dvcmQiOm51bGwsIm5pY2tuYW1lIjoidGVzdCIsInBob25lIjoiMDEwIiwiaW50ZXJlc3QiOm51bGwsInVzZXJwcm9maWxlIjoiMXVzZXJwcm9maWxlLnBuZyIsInJvbGUiOm51bGwsImF1dGhvcml6YXRpb24iOiJOIn19.1bmtn-QzdLZleERKh24o18FBEPo7FxPZ6_ueLzSv5rU'
    // 'abc' : 'abc',
  },
  
  
});
