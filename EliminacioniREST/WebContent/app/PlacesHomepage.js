Vue.component("places", {
    data(){
        return{
           places:[], 
            selected:null,
            search: {name:"", location:"", type:-1, grade:-1},
            check: false,
            allPlaces:[]
        }
    },
template: `
    
        <div>
                        <table style="margin-left: 200px; margin-top: 20px;" >
                          <tr> 
                            <td style="width: 250px"> <input style="width: 150px" type="text" class="form-control search-slt" placeholder="Naziv" v-model="search.name"> </td>
                            <td style="width: 250px" > <input style="width: 150px"  type="text" class="form-control search-slt" placeholder="Lokacija" v-model="search.location"> </td>
                              <td style="width: 300px"> 
                                
                                                    <select v-model="search.type" style="height: 35px; width: 120px; background-color:#6c757d; color:white;  border-radius: 4px; font-size: 14px;">Tip
                                                     
                                                    <option v-bind:value=-1>Tip objekta</option>
                                                    <option  v-bind:value="0" style=" margin-left: 5px;background-color:white; color: black">Teretana</option>
                                                    <option  v-bind:value="1" style="margin-left: 5px; background-color:white; color: black">Bazen</option>
                                                    <option  v-bind:value="2" style="margin-left: 5px; background-color:white; color: black">Plesni studio</option>
                                                    <option  v-bind:value="3" style="margin-left: 5px; background-color:white; color: black">Sportski centar</option>
     
                                                    </select>
                                
                            </td>
                              <td style="width: 250px"> 
                            
                              <select   v-model="search.grade" style="height: 35px; width: 147px; background-color:#6c757d; color:white;  border-radius: 4px;font-size: 14px;" > Ocena
                               <option v-bind:value=-1>Ocena</option>
                              <option  v-bind:value="5" style=" background-color:white; color: black">5</option>
                              <option  v-bind:value="4" style=" background-color:white; color: black">4</option>
                              <option  v-bind:value="3" style=" background-color:white; color: black">3</option>
                              <option  v-bind:value="2" style=" background-color:white; color: black">2</option>
                              <option  v-bind:value="1" style=" background-color:white; color: black">1</option>
                              </select>
                           
                            </td>
                            <td style="width: 250px"> <button @click=searchPlaces type="button" class="btn btn-danger wrn-btn btn-search" >Pretrazi </button></td>
                         
                            </tr>
                        </table>
		<div class="container">
    		<!-- filtriranje -->
                  <table style= " margin-left:-20px;">
                <tr> 

                    <td>  </td> 
                    <td style= "font-size: 16px;"> Filtrirajte sportske objekte po tipu:  </td> 
                    <td>
                    <div class="dropdown"> <button class="btn btn-secondary dropdown-toggle"  id="dropdownMenuButton" type="button" data-toggle="dropdown" style= "margin-left:10%;" aria-haspopup="true" aria-expanded="false">
                            Tip objekta
                            </button>
            
                            <span class="dropdown-menu" aria-labelledby="dropdownMenuButton" >
                            <button class="dropdown-item" type="button" v-on:click="filterType('TERETANA')">TERETANA</button>
                            <button class="dropdown-item" type="button" v-on:click="filterType('BAZEN')">BAZEN</button>
                            <button class="dropdown-item" type="button" v-on:click="filterType('PLESNI_STUDIO')">PLESNI STUDIO</button>
							<button class="dropdown-item" type="button" v-on:click="filterType('SPORTSKI_CENTAR')">SPORTSKI CENTAR</button>

                            </span>
                       </div>
                            </td> 


                        <td>  </td> 
                        <td>  </td> 
                        <td>  </td> 
                        <td  style= "font-size: 16px; padding-left:20px;"> ili po statusu:  </td> 
                        <td>
                        <div class="dropdown"> 
                        <button class="btn btn-secondary dropdown-toggle" type="button" data-toggle="dropdown"  id="dropdownMenu2" style="margin-left:20%; margin-top:20%;" aria-haspopup="true" aria-expanded="false" >
                           	Status
                            </button>
            
                            <span class="dropdown-menu" aria-labelledby="dropdownMenu2" >
                            <button class="dropdown-item" type="button" v-on:click="filterStatus('OTVORENO')">OTVORENO</button>
                            <button class="dropdown-item" type="button" v-on:click="filterStatus('ZATVORENO')">ZATVORENO</button>
                          </span>
                          </div>
                        </td>
                      <td style="width:50px;"> </td>
        <td><button class="btn btn-secondary" type="button"  v-if="check" v-on:click="reset()">x</button> </td> 
                      
                </tr>

            </table>

			<!-- sortiranje --> 
               <table style= " margin-left:-20px; margin-bottom: 3em;">
                <tr> 

                    <td>  </td> 
                    <td style= "font-size: 16px;"> Sortiranje sportskih objekata po nekom od zadatih kriterijuma:  </td> 
                    <td> 
                    <div class="dropdown">
                    <button class="btn btn-secondary dropdown-toggle" type="button" data-toggle="dropdown" id="dropdownMenuSort" style= "margin-left:10%; margin-top:10%" >
                            Sortiraj
                            </button>
            
                            <span class="dropdown-menu" aria-labelledby="dropdownMenuSort" >
                            <button class="dropdown-item" type="button" v-on:click="sortNameAsc()">Naziv rastuce</button>
                            <button class="dropdown-item" type="button" v-on:click="sortNameDesc()">Naziv opadajuce</button>
                            <button class="dropdown-item" type="button" v-on:click="sortLocationAsc()">Lokacija rastuce</button>
                            <button class="dropdown-item" type="button" v-on:click="sortLocationDesc()">Lokacija opadajuce</button>
                            <button class="dropdown-item" type="button" v-on:click="sortGradeameAsc()">Ocena rastuce</button>
                            <button class="dropdown-item" type="button" v-on:click="sortGradeDesc()">Ocena opadajuce</button>
                            </span>

                            </div>
                        </td> 
      
                </tr>
				<tr> </tr>
            </table>  
		</div>
              
            
            <div  style="margin-left: -15rem; margin-top: 3rem;"> 
               <div class=" tab-pane container active">

                    <div v-for="p in places" :key="'A' + p.name" class="row"  >
                          <div v-if="p.status == 'OTVORENO'" class = "col" style="border-radius: 4px;margin-left: -3rem;">
                              <div class="col-picture">
                                  <div><img :src="'pictures/'+p.logo" style="height:220px !important; width:300px !important;border-radius: 4px; margin-right: 3em; " class="img-thumbnail" >
                                  
                                </div>
                              </div>
                          </div>
                          <div v-if="p.status == 'OTVORENO'" class="col" style=" margin-top: 3rem;">
                              <h4 style="width: 600px;" class="text">Naziv: {{p.name}} </h4>
                              <h4 style="width: 600px;" class="text">Tip objekta: {{p.type}} </h4>
                              <h4 style="width: 600px;" class="text">Lokacija: {{p.address.city}} </h4>
                              <h4 style="width: 600px;" class="text">Prosecna ocena: {{p.grade}} </h4>
							  <h4 style="width: 600px;" class="text">Radno vreme: {{p.workingTime}} </h4>
                              <h4 style="width: 600px;" class="text">Status: {{p.status}} </h4>
							
                          </div>
                          
                              <div class="col" v-if="p.status == 'OTVORENO'">
			                        <div class="buttons btn-group-vertical">
 										<button style="width:100px; margin-top:40px;" type="button" class="btn btn-secondary" v-on:click="goToObject(p.id)" >Prikazi</button>
			                       </div>
			                       </div>
                        
                    </div>
  <hr/>
                    <div v-for="p in places" :key="'B' + p.name" class="row"  >
                          <div v-if="p.status != 'OTVORENO'" class = "col" style="border-radius: 4px;margin-left: -3rem;">
                              <div class="col-picture">
                                  <div><img :src="'pictures/'+p.logo" style="height:220px !important; width:300px !important;border-radius: 4px; margin-right: 3em; " class="img-thumbnail" >
                                  
                                </div>
                              </div>
                          </div>
                          <div v-if="p.status != 'OTVORENO'" class="col" style=" margin-top: 3rem;">
                              <h4 style="width: 600px;" class="text">Naziv: {{p.name}} </h4>
                              <h4 style="width: 600px;" class="text">Tip objekta: {{p.type}} </h4>
                              <h4 style="width: 600px;" class="text">Lokacija: {{p.address.city}} </h4>
                              <h4 style="width: 600px;" class="text">Prosecna ocena: {{p.grade}} </h4>
							  <h4 style="width: 600px;" class="text">Radno vreme: {{p.workingTime}} </h4>
                              <h4 style="width: 600px;" class="text">Status: {{p.status}} </h4>
                          </div>
                          
                          
                           <div class="col" v-if="p.status != 'OTVORENO'">
			                        <div class="buttons btn-group-vertical">
 										<button style="width:100px; margin-top:40px;" type="button" class="btn btn-secondary" v-on:click="goToObject(p.id)" >Prikazi</button>
			                       </div>
			                  </div>
                           </div>
                    </div>

                    

                    
                 
                    
                </div>

            </div>
            
      
    </div>
    

         
`,
methods:{
    searchPlaces: function() {
      this.places = []

      if(!this.search.name && !this.search.location && this.search.type == -1 && this.search.grade == -1){
        this.places = this.allPlaces
        return
      }

      var tmpList1 = []
      // if(this.search.name) {
        for (let i = 0; i < this.allPlaces.length; i++) {
/*          console.log("this.allPlaces[i].name.toLowerCase()" + this.allPlaces[i].name.toLowerCase())
          console.log(this.search.name)*/
            if (this.allPlaces[i].name.toLowerCase().includes(this.search.name)) {
/*              console.log("\nIDEMOOOO" + JSON.stringify(this.allPlaces[i]))*/
                tmpList1.push(this.allPlaces[i])
/*              console.log("\nIDEMOOOO222222" + JSON.stringify(tmpList1))*/
            }
        }
		console.log("tmpList1:		" + tmpList1)
        // places.push(tmpList.slice())
      // }

      var tmpList2 = []
      // if(this.search.location) {
        for (let i = 0; i < tmpList1.length; i++) {
            if (tmpList1[i].address.city.toLowerCase().includes(this.search.location)) {
                tmpList2.push(tmpList1[i])
            }
        }
		console.log("tmpList2:		" + tmpList2)
        // places.push(tmpList.slice())
      // }

      // this.places = tmpList2.slice()

      var tmpList3 = tmpList2.slice()
      if(this.search.type != -1) {
      tmpList3 = []
      var check = ""
      if(this.search.type == 0) check="TERETANA"
      if(this.search.type == 1) check="BAZEN"
      if(this.search.type == 2) check="PLESNI_STUDIO"
      if(this.search.type == 3) check="SPORTSKI_CENTAR"
        for (let i = 0; i < tmpList2.length; i++) {
            console.log("\ntmpList2[i].type: " + tmpList2[i].type)
            console.log("\ncheck: " + check)
            if (tmpList2[i].type.includes(check)) {
                tmpList3.push(tmpList2[i])
            }
        }
      //   // places.push(tmpList.slice())
       }
		console.log("tmpList3:		" + tmpList3)

      var tmpList4 = tmpList3.slice()
       if(this.search.grade != -1) {
        tmpList4 = []
        for (let i = 0; i < tmpList3.length; i++) {
          console.log("\ntmpList3[i].type: " + tmpList3[i].grade.toString())
            console.log("\nthis.search.grade: " + this.search.grade)
            if (tmpList3[i].grade.toString().includes(this.search.grade.toString())) {
                tmpList4.push(tmpList3[i])
            }
        }
      //   // places.push(tmpList.slice())
      }

	console.log("tmpList4:		" + tmpList4)


     this.places = tmpList4.slice()
      // console.log("\ntmpList: " + JSON.stringify(tmpList4))
      console.log("\nplaces: " + JSON.stringify(this.places))
    },
    
     goToObject : function (idR) {
        
            this.$router.push({path: `/objekat`, query:{ id: idR}})
        },

    filterStatus: function (status){
        this.places = this.places.filter(place => place.status === status);
        
        this.check = true
    },
    
    filterType: function (type){
        this.places = this.places.filter(place => place.type === type);
        
        this.check = true
    },
    reset: function(){

	this.places = this.allPlaces
	this.check = false
    
    },
    sortNameAsc: function() {
        function compare(a, b) {
          if (a.name < b.name)
            return -1;
          if (a.name > b.name)
            return 1;
          return 0;
        }

        return this.places.sort(compare);
    }, 
    sortNameDesc: function() {
        function compare(a, b) {
          if (a.name < b.name)
            return 1;
          if (a.name > b.name)
            return -1;
          return 0;
        }
        
        return this.places.sort(compare);
    },
        sortLocationAsc: function() {
        function compare(a, b) {
          if (a.address.city < b.address.city)
            return -1;
          if (a.address.city > b.address.city)
            return 1;
          return 0;
        }

        return this.places.sort(compare);
    }, 
    sortLocationDesc: function() {
        function compare(a, b) {
          if (a.address.city < b.address.city)
            return 1;
          if (a.address.city > b.address.city)
            return -1;
          return 0;
        }
        
        return this.places.sort(compare);
    },
        sortGradeAsc: function() {
        function compare(a, b) {
          if (a.grade < b.grade)
            return -1;
          if (a.grade > b.grade)
            return 1;
          return 0;
        }

        return this.place.sort(compare);
    }, 
    sortGradeDesc: function() {
        function compare(a, b) {
          if (a.grade < b.grade)
            return 1;
          if (a.grade > b.grade)
            return -1;
          return 0;
        } 
        return this.places.sort(compare);
    }
},
computed: {
    
  },
mounted(){
    axios.get("/EliminacioniREST/rest/place/getAllPlaces")
    .then( response => {
      this.places = response.data
      this.allPlaces = response.data
    }).catch( err => {
      console.log(err)
    });
}
});