import { get, post } from '../axios'

export function login(params){
    return post('/login',params,true)
}
export function logout(){
    return get('/logout')
}
export function register(params){
    return post('/user/register',params,true)
}
export function sengEmail(params){
    return post('/user/email/sent',params,true)
}
export function sentEmailForPassword(params){
    return post('/user/email/sent/password',params,true)
}
export function findPassword(params){
    return post('/user/email/find/password',params,true)
}