const PlacesComponent = {template: '<places></places>'}
const PlaceProfileComponent = {template: '<place-profile></place-profile>'}


const router = new VueRouter({

    mode: 'hash',
    routes:[
        {path : '/', component: PlacesComponent}, //objekti na  pocetnoj
        {path: '/objekat', component: PlaceProfileComponent}
       
     
    ]
})

var fitPassApp = new Vue({
    router,
    el: '#pocetnaID'
});