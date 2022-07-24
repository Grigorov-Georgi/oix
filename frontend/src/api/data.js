import * as api from './api.js'

export const login = api.login;
export const register = api.register;
export const logout = api.logout;

// export async function getAllGames(){
//     return api.get('/data/games?sortBy=_createdOn%20desc&distinct=category');
// }

export async function createUser(user){
    return api.post('/register', user)
}

// export async function getGameById(id){
//     return api.get('/data/games/' + id);
// }

// export async function deleteById(id){
//     return api.del('/data/games/' + id);
// }

// export async function editGame(id, book){
//     return api.put('/data/games/' + id, book);
// }

// export async function createComment(comment){
//     return api.post('/data/comments', comment)
// }

// export async function loadComments(gameId){
//     return api.get(`/data/comments?where=gameId%3D%22${gameId}%22`)
// }