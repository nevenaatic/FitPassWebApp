Vue.component("objekti-kupac", {
    data(){
        return{
            places:[], 
            selected:null,
            search: {name:"", location:"", type:6, grade:""}
        }
    },
template: `
<div class="containerInfo">

	<!--pretraga-->
	<div class="row" style="width:1400px !important; margin-left:8%;">
		<div class="col-lg-12">
					    <div class="row" style="width:1400px !important;">
										        <div class="col-lg-2 col-md-3 col-sm-12 p-0 search">
										            <input type="text" class="form-control search-slt" placeholder="Naziv objekta" v-model="search.name">
										        </div>
										        <div class="col-lg-2 col-md-3 col-sm-12 p-0 search" >
										            <input type="text" class="form-control search-slt" placeholder="Lokacija objekta" v-model="search.location">
										        </div>
                                                <div class="dropdown col-lg-2 col-md-3 col-sm-12 p-0 filt">
                                                    <select v-model="search.type" style="height: 35px; width: 150px; background-color:#6c757d; color:white;  border-radius: 4px;">Tip
                                                     <option value=6>Tip restorana</option>
                                                    <option  v-bind:value="0" style=" background-color:white; color: black">Italijanski</option>
                                                    <option  v-bind:value="1" style=" background-color:white; color: black">Kineski</option>
                                                    <option  v-bind:value="2" style=" background-color:white; color: black">Pica</option>
                                                    <option  v-bind:value="3" style=" background-color:white; color: black">Rostilj</option>
                                                    <option  v-bind:value="4" style=" background-color:white; color: black">Riblji</option>
                                                    <option  v-bind:value="5" style=" background-color:white; color: black">Veganski</option>
                                                    </select>
                                                   
                                                </div>
                                                <div class="dropdown col-lg-2 col-md-3 col-sm-12 p-0 filt">
                                                    <select v-model="search.grade" style="height: 35px; width: 150px; background-color:#6c757d; color:white;  border-radius: 4px;"> Ocena
                                                     <option value="">Ocena</option>
                                                    <option  v-bind:value="5" style=" background-color:white; color: black">5</option>
                                                    <option  v-bind:value="4" style=" background-color:white; color: black">4</option>
                                                    <option  v-bind:value="3" style=" background-color:white; color: black">3</option>
                                                    <option  v-bind:value="2" style=" background-color:white; color: black">2</option>
                                                    <option  v-bind:value="1" style=" background-color:white; color: black">1</option>
                                                    </select>
                                                    </div>
                                                
                                                <div class="col-lg-1 col-md-3 col-sm-12 btn-search">
                                                    <button type="button" class="btn btn-danger wrn-btn" v-on:click="pretrazi()">Pretrazi kombinovano</button>
                                                </div>
					    </div>
		</div>
	</div>
		
		
		
		            <div class=" tab-pane container active">
		            
			            
			                       <div v-for="p in places" :key="p.name" class="row"  >
                          <div v-if="p.status == 'OTVORENO'" class = "col" style="border-radius: 4px;margin-left: -3rem;">
                              <div class="col-picture">
                                  <div><img :src="'../pictures/'+p.logo" style="height:220px !important; width:300px !important;border-radius: 4px; margin-right: 3em; " class="img-thumbnail" >
                                  
                                </div>
                              </div>
                          </div>
                          <div v-if="p.status == 'OTVORENO'" class="col" style="margin-left: -30rem; margin-top: 3rem;">
                              <h4 style="width: 600px;" class="text">Naziv: {{p.name}} </h4>
                              <h4 style="width: 600px;" class="text">Tip objekta: {{p.type}} </h4>
                              <h4 style="width: 600px;" class="text">Lokacija: {{p.address.city}} </h4>
                              <h4 style="width: 600px;" class="text">Prosecna ocena: {{p.grade}} </h4>
							  <h4 style="width: 600px;" class="text">Radno vreme: {{p.workingTime}} </h4>
                              <h4 style="width: 600px;" class="text">Status: {{p.status}} </h4>
                          </div>
			                
			                 <hr/>
			                
		             </div>
		             
		                   <div v-for="p in places" :key="p.name" class="row"  >
                          <div v-if="p.status != 'OTVORENO'" class = "col" style="border-radius: 4px;margin-left: -3rem;">
                              <div class="col-picture">
                                  <div><img :src="'../pictures/'+p.logo" style="height:220px !important; width:300px !important;border-radius: 4px; margin-right: 3em; " class="img-thumbnail" >
                                  
                                </div>
                              </div>
                          </div>
                          <div v-if="p.status != 'OTVORENO'" class="col" style="margin-left: -30rem; margin-top: 3rem;">
                              <h4 style="width: 600px;" class="text">Naziv: {{p.name}} </h4>
                              <h4 style="width: 600px;" class="text">Tip objekta: {{p.type}} </h4>
                              <h4 style="width: 600px;" class="text">Lokacija: {{p.address.city}} </h4>
                              <h4 style="width: 600px;" class="text">Prosecna ocena: {{p.grade}} </h4>
							  <h4 style="width: 600px;" class="text">Radno vreme: {{p.workingTime}} </h4>
                              <h4 style="width: 600px;" class="text">Status: {{p.status}} </h4>
                          </div>
                          <hr/>
                    </div>
		             
		             
		             
		             
</div>   
`,
methods:{
        getSelected: function(place){
        this.selected= place;
        },
        goToObjekt : function (idR) {
           // this.$router.push({path: `/restoran`, query:{ id: idR}})
        },
        pretrazi: function(){
            axios.post('/EliminacioniREST/rest/place/searchPlaces', this.search)
            .then(response => {
               this.places = response.data
            })
            .catch(function(error){
                console.log(error)
            })
        },
},
mounted(){
    axios.get("/EliminacioniREST/rest/place/getAllPlaces")
      .then( response => {
          this.places = response.data
          console.log(this.places)
      })
      .catch(function(error){
          console.log(error)
      })
}
})