const PlacesComponent = {template: '<places-admin></places-admin>'}
const AllUsersComponent = {template: '<allUsers-admin></allUsers-admin>'}
const AdminProfileComponent = {template: '<admin-profile></admin-profile>'}
const AdminEditProfileComponent = {template: '<admin-editProfile></admin-editProfile>'}
const NewPlaceComponent = {template: '<admin-newPlace></admin-newPlace>'}
const PlaceProfileComponent = {template: '<admin-placeProfile></admin-placeProfile>'}
const CommentsComponent = {template: '<admin-comments></admin-comments>'}
const CommentsUnacceptedComponent = {template: '<admin-unaccepted></admin-unaccepted>'}



const router = new VueRouter({
    mode: 'hash',
    routes:[
        {path : '/', component: PlacesComponent},
        {path : '/profil', component: AdminProfileComponent}, //profil
        {path : '/izmeniProfil', component: AdminEditProfileComponent}, //izmena profila
        {path : '/korisnici', component: AllUsersComponent}, //svi korisnici
       
        {path : '/noviObjekat', component: NewPlaceComponent}, //novi objekat
        {path : '/objekat', component: PlaceProfileComponent}, 
         {path : '/komentari', component: CommentsComponent}, 
         {path : '/odbijeni', component: CommentsUnacceptedComponent}, 
    ]
})

var adminRouter = new Vue({
    router,
    el: '#adminRouter'
});