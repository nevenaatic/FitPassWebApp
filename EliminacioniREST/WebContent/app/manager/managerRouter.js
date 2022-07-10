const PlaceComponent = {template: '<place-manager></place-manager>'}
const AllUsersComponent = {template: '<allUsers-manager></allUsers-manager>'}
const AdminProfileComponent = {template: '<admin-profile></admin-profile>'}
const AdminEditProfileComponent = {template: '<admin-editProfile></admin-editProfile>'}
const PlaceCoachesComponent = {template: '<place-coaches></place-coaches>'}
const CommentComponent = {template: '<manager-comment></manager-comment>'}
const EditPlaceComponent = {template: '<edit-training></edit-training>'}
const AddTrainingComponent = {template: '<add-training></add-training>'}


const router = new VueRouter({
    mode: 'hash',
    routes:[
        {path : '/', component: PlaceComponent},  //profil objekta
        {path : '/profil', component: AdminProfileComponent}, //profil menadzera
        {path : '/izmeniProfil', component: AdminEditProfileComponent}, //izmena profila
       {path : '/musterije', component: AllUsersComponent}, //svi korisnici,
        {path : '/treneri', component: PlaceCoachesComponent}, //svi treneri koji su u tom objektu
       {path : '/komentari', component: CommentComponent}, 
       {path : '/izmeniTrening', component: EditPlaceComponent}, 
       {path : '/dodajTrening', component: AddTrainingComponent}, 
    ]
})

var managerRouter = new Vue({
    router,
    el: '#managerRouter'
});