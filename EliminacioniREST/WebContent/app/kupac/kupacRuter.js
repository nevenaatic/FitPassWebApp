const PrikazObjekataComponent = {template: '<objekti-kupac></objekti-kupac>'}
const ProfilKupacComponent = {template: '<profil-kupac></profil-kupac>'}
const IzmeniProfilComponent = {template: '<izmeniProfil-kupac></izmeniProfil-kupac>'}
const PlaceProfileComponent = {template: '<kupac-placeProfile></kupac-placeProfile>'}
const MembershipComponent = {template: '<kupac-clanarina></kupac-clanarina>'}


const router = new VueRouter({
    mode: 'hash',
    routes:[
        {path : '/', component: PrikazObjekataComponent},//prikaz svih objekata
        {path : '/profil', component: ProfilKupacComponent}, //profil
        {path : '/izmeniProfil', component: IzmeniProfilComponent}, //izmena profila
     	{path : '/objekat', component: PlaceProfileComponent}, //profil objekta
     	{path : '/clanarine', component: MembershipComponent}, //clanarine
        
    ]
})

var kupacApp = new Vue({
    router,
    el: '#kupacGlavniPrikaz'
});