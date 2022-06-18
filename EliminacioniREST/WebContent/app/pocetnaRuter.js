const PlacesComponent = {template: '<places></places>'}



const router = new VueRouter({

    mode: 'hash',
    routes:[
        {path : '/', component: PlacesComponent}, //objekti na  pocetnoj
       
     
    ]
})

var fitPassApp = new Vue({
    router,
    el: '#pocetnaID'
});