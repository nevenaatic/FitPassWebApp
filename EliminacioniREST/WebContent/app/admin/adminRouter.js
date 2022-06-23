const PlacesComponent = {template: '<places-admin></places-admin>'}
const AllUsersComponent = {template: '<allUsers-admin></allUsers-admin>'}
const AdminProfileComponent = {template: '<admin-profile></admin-profile>'}
const AdminEditProfileComponent = {template: '<admin-editProfile></admin-editProfile>'}




const router = new VueRouter({
    mode: 'hash',
    routes:[
        {path : '/', component: PlacesComponent},
        {path : '/profil', component: AdminProfileComponent}, //profil
        {path : '/izmeniProfil', component: AdminEditProfileComponent}, //izmena profila
       {path : '/korisnici', component: AllUsersComponent}, //svi korisnici
        
    ]
})

var adminRouter = new Vue({
    router,
    el: '#adminRouter'
});