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

    
              
            
            <div  style="margin-left: -15rem; margin-top: 3rem;"> 
               <div class=" tab-pane container active">

                    <div v-for="p in places" :key="p.name" class="row"  >
                          <div v-if="p.status == 'OTVORENO'" class = "col" style="border-radius: 4px;margin-left: -3rem;">
                              <div class="col-picture">
                                  <div><img :src="'pictures/'+p.logo" style="height:220px !important; width:300px !important;border-radius: 4px; margin-right: 3em; " class="img-thumbnail" >
                                  
                                </div>
                              </div>
                          </div>
                          <div v-if="p.status == 'OTVORENO'" class="col" style="margin-left: -30rem; margin-top: 3rem;">
                              <h4 style="width: 600px;" class="text">Naziv: {{p.name}} </h4>
                              <h4 style="width: 600px;" class="text">Tip objekta: {{p.type}} </h4>
                              <h4 style="width: 600px;" class="text">Lokacija: {{p.address.city}} </h4>
                              <h4 style="width: 600px;" class="text">Prosecna ocena: {{p.grade}} </h4>
                              <h4 style="width: 600px;" class="text">Status: {{p.status}} </h4>
                          </div>
                          <hr/>
                    </div>

                    <div v-for="p in places" :key="p.name" class="row"  >
                          <div v-if="p.status != 'OTVORENO'" class = "col" style="border-radius: 4px;margin-left: -3rem;">
                              <div class="col-picture">
                                  <div><img :src="'pictures/'+p.logo" style="height:220px !important; width:300px !important;border-radius: 4px; margin-right: 3em; " class="img-thumbnail" >
                                  
                                </div>
                              </div>
                          </div>
                          <div v-if="p.status != 'OTVORENO'" class="col" style="margin-left: -30rem; margin-top: 3rem;">
                              <h4 style="width: 600px;" class="text">Naziv: {{p.name}} </h4>
                              <h4 style="width: 600px;" class="text">Tip objekta: {{p.type}} </h4>
                              <h4 style="width: 600px;" class="text">Lokacija: {{p.address.city}} </h4>
                              <h4 style="width: 600px;" class="text">Prosecna ocena: {{p.grade}} </h4>
                              <h4 style="width: 600px;" class="text">Status: {{p.status}} </h4>
                          </div>
                          <hr/>
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
          console.log("this.allPlaces[i].name.toLowerCase()" + this.allPlaces[i].name.toLowerCase())
          console.log(this.search.name)
            if (this.allPlaces[i].name.toLowerCase().includes(this.search.name)) {
              console.log("\nIDEMOOOO" + JSON.stringify(this.allPlaces[i]))
                tmpList1.push(this.allPlaces[i])
              console.log("\nIDEMOOOO222222" + JSON.stringify(tmpList1))
            }
        }
        // places.push(tmpList.slice())
      // }

      var tmpList2 = []
      // if(this.search.location) {
        for (let i = 0; i < tmpList1.length; i++) {
            if (tmpList1[i].address.city.toLowerCase().includes(this.search.location)) {
                tmpList2.push(tmpList1[i])
            }
        }
        // places.push(tmpList.slice())
      // }

      var tmpList3 = []
      var check = ""
      if(this.search.type == 0) check="TERETANA"
      if(this.search.type == 1) check="BAZEN"
      if(this.search.type == 2) check="PLESNI_STUDIOS"
      if(this.search.type == 3) check="SPORTSKI_CENTAR"
      // if(this.search.type == "") {
        for (let i = 0; i < tmpList2.length; i++) {
            console.log("\ntmpList2[i].type: " + tmpList2[i].type)
            console.log("\ncheck: " + check)
            if (tmpList2[i].type.includes(check)) {
                tmpList3.push(tmpList2[i])
            }
        }
      //   // places.push(tmpList.slice())
      // }

      var tmpList4 = []
      // if(this.search.grade = "") {
        for (let i = 0; i < tmpList3.length; i++) {
          console.log("\ntmpList3[i].type: " + tmpList3[i].grade.toString())
            console.log("\nthis.search.grade: " + this.search.grade)
            if (tmpList3[i].grade.toString().includes(this.search.grade)) {
                tmpList4.push(tmpList3[i])
            }
        }
      //   // places.push(tmpList.slice())
      // }

      this.places = tmpList4.slice()
      console.log("\ntmpList: " + JSON.stringify(tmpList4))
      console.log("\nplaces: " + JSON.stringify(this.places))
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