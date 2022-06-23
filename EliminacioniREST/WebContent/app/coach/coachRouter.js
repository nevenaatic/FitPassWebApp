
const AdminProfileComponent = {template: '<admin-profile></admin-profile>'}
const AdminEditProfileComponent = {template: '<admin-editProfile></admin-editProfile>'}

const PersonalTrainingsComponent = {template: '<personal-trainings></personal-trainings>'}
const TrainingsComponent = {template: '<coach-trainings></coach-trainings>'}
const GroupTrainingsComponent = {template: '<group-trainings></group-trainings>'}



const router = new VueRouter({
    mode: 'hash',
    routes:[
        {path : '/', component: TrainingsComponent},  //svi treninzi
        {path : '/profil', component: AdminProfileComponent}, //profil trenera
        {path : '/izmeniProfil', component: AdminEditProfileComponent}, //izmena profila
       {path : '/personalni', component: PersonalTrainingsComponent}, 
        {path : '/grupni', component: GroupTrainingsComponent}, 
           
    ]
})

var coachRouter = new Vue({
    router,
    el: '#coachRouter'
});