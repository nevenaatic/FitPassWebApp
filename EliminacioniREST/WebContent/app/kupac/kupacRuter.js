const PrikazObjekataComponent = {template: '<objekti-kupac></objekti-kupac>'}
const ProfilKupacComponent = {template: '<profil-kupac></profil-kupac>'}
const IzmeniProfilComponent = {template: '<izmeniProfil-kupac></izmeniProfil-kupac>'}




const router = new VueRouter({
    mode: 'hash',
    routes:[
        {path : '/', component: PrikazObjekataComponent},//prikaz svih objekata
        {path : '/profil', component: ProfilKupacComponent}, //profil
        {path : '/izmeniProfil', component: IzmeniProfilComponent}, //izmena profila
     
        
    ]
})

var kupacApp = new Vue({
    router,
    el: '#kupacGlavniPrikaz'
});