import request from '@/axios'
import { RequestResponse } from './types'

// let baseURL = 'http://192.168.124.3:8080/cms';
let baseURL = 'http://127.0.0.1:8080/cms';


export const pageLabel = () => {
  return request.get<IResponse<RequestResponse>>({
    url: baseURL+ '/category/list',
    // params: {
    //   // code: 'pg_index',
    //   // page: 1,
    //   // size: 50,
    //   // version: '1.1.1',
    //   // pid: 1,
    // },
  });
};
export const dishes = ( categoryId:number ) => {
  return request.get<IResponse<RequestResponse>>({
    url: baseURL+ '/dish/list/'+categoryId,
    // params: {
    //   // code: 'pg_index',
    //   // page: 1,
    //   // size: 50,
    //   // version: '1.1.1',
    //   // pid: 1,
    // },
  });
};
export const addDishes = ( data:any ) => {
  return request.post<IResponse<RequestResponse>>({
    url: baseURL+ '/dish/save',
    data
  });
};
export const addDishCount = ( dishId:number ) => {
  return request.get<IResponse<RequestResponse>>({
    url: baseURL+ '/dish/addCount/'+dishId,

  });
};
