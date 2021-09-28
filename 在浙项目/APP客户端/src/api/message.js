import { get, getWithPath,post } from '../axios'

export function listArticleMsg(params){
    return getWithPath('/message/'+params+'/list')
}  
export function saveMsgByArticle(params){
    return post('/message/save',params,true)
}
export function listArticleMsgByUId(params){
    return getWithPath('/message/'+params+'/get')
}  
export function getMessageWithoutClickStar(params){
    return getWithPath('/message/'+params+'/without/click/save')
}