const PlaceComponent = {template: '<place-manager></place-manager>'}
const AllUsersComponent = {template: '<allUsers-manager></allUsers-manager>'}
const AdminProfileComponent = {template: '<admin-profile></admin-profile>'}
const AdminEditProfileComponent = {template: '<admin-editProfile></admin-editProfile>'}
const PlaceCoachesComponent = {template: '<place-coaches></place-coaches>'}
const TrainingsComponent = {template: '<manager-trainings></manager-trainings>'}



const router = new VueRouter({
    mode: 'hash',
    routes:[
        {path : '/', component: PlaceComponent},  //profil objekta
        {path : '/profil', component: AdminProfileComponent}, //profil menadzera
        {path : '/izmeniProfil', component: AdminEditProfileComponent}, //izmena profila
       {path : '/musterije', component: AllUsersComponent}, //svi korisnici,
        {path : '/treneri', component: PlaceCoachesComponent}, //svi treneri koji su u tom objektu
           {path : '/treninzi', component: TrainingsComponent}, //svi dostupni treninzi u objektu
    ]
})

var managerRouter = new Vue({
    router,
    el: '#managerRouter'
});