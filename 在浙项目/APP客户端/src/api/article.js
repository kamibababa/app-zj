import { get, getWithPath,post } from '../axios'

export function getArticleById(params){
    return getWithPath('/article/'+params+'/get')
}
export function listArticleByType2(){
    return getWithPath('/article/list/2')
}
export function listArticleByType3(){
    return getWithPath('/article/list/3')
}
export function listArticleByType4(){
    return getWithPath('/article/list/4')
}
export function getArticleRedisById(params){
    return getWithPath('/article/'+params+'/lookWithoutClick')
}
export function getArticleRedisLookById(aId,params){
    return get('/article/'+aId+'/look',params)
}
export function getArticleStar(aId){
    return getWithPath('/article/'+aId+'/without/click/save')
}
export function getArticleAddStar(aId,params){
    return get('/article/'+aId+'/click/save',params)
}
export function removeArticleStar(aId,params){
    return get('/article/'+aId+'/click/remove',params)
}