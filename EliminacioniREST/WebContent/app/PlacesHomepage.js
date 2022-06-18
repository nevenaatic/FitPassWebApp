Vue.component("places", {
    data(){
        return{
           places:[], 
            selected:null,
            search: {name:"", location:"", type:6, grade:""},
            check: false
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
                                                     
                                                    <option value=6>Tip objekta</option>
                                                    <option  v-bind:value="0" style=" margin-left: 5px;background-color:white; color: black">Teretana</option>
                                                    <option  v-bind:value="1" style="margin-left: 5px; background-color:white; color: black">Bazen</option>
                                                    <option  v-bind:value="2" style="margin-left: 5px; background-color:white; color: black">Plesni studio</option>
                                                    <option  v-bind:value="3" style="margin-left: 5px; background-color:white; color: black">Sportski centar</option>
     
                                                    </select>
                                
                            </td>
                              <td style="width: 250px"> 
                            
                              <select   v-model="search.grade" style="height: 35px; width: 147px; background-color:#6c757d; color:white;  border-radius: 4px;font-size: 14px;" > Ocena
                               <option value="">Ocena</option>
                              <option  v-bind:value="5" style=" background-color:white; color: black">5</option>
                              <option  v-bind:value="4" style=" background-color:white; color: black">4</option>
                              <option  v-bind:value="3" style=" background-color:white; color: black">3</option>
                              <option  v-bind:value="2" style=" background-color:white; color: black">2</option>
                              <option  v-bind:value="1" style=" background-color:white; color: black">1</option>
                              </select>
                           
                            </td>
                            <td style="width: 250px"> <button type="button" class="btn btn-danger wrn-btn btn-search" >Pretrazi </button></td>
                         
                            </tr>
                        </table>

    
              
            
            <div  style="margin-left: -15rem; margin-top: 3rem;"> 
               <div class=" tab-pane container active">
                    <div class="row"  >
                          <div class = "col" style="border-radius: 4px;margin-left: -3rem;">
                              <div class="col-picture">
                                  <div><img src="pictures/firstPhoto.jpg" style="height:220px !important; width:300px !important;border-radius: 4px; margin-right: 3em; " class="img-thumbnail" >
                                  
                                </div>
                              </div>
                          </div>
                          <div class="col" style="margin-left: -30rem; margin-top: 3rem;">
                              <h4 style="width: 600px;" class="text">Naziv:  </h4>
                              <h4 style="width: 600px;" class="text">Tip objekta:  </h4>
                              <h4 style="width: 600px;" class="text">Lokacija:  </h4>
                              <h4 style="width: 600px;" class="text">Prosecna ocena: </h4>
                              <h4 style="width: 600px;" class="text">Status: </h4>
                          </div>
                    </div>

                      <hr/>

                 
                    
                </div>

            </div>
            
      
    </div>
    

         
`,
methods:{
    
},
computed: {

  },
mounted(){
    
}
});