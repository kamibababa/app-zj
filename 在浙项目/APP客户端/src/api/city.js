import { get, getWithPath,post } from '../axios'

export function getCityById(params){
    return getWithPath('/city/'+params+'/get')
}
export function listCityPlase(params){
    return getWithPath('/redBase/'+params+'/list')
}
export function listCityHero(params){
    return getWithPath('/character/'+params+'/list')
}
export function listCityArticle(params){
    return getWithPath('/article/'+params+'/list')
}