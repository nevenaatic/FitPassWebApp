Vue.component("kupac-clanarina", {
    data(){
        return{
          
      memberships: []
        }
    },

template: `
<div class="containerInfo">

		 <div class=" tab-pane container active" style="margin-top: 3rem;">
		            
			         
			         	           <!--tabela-->
  <div >
    <table class="table table-hover" style="width: 80%; margin-left: 12%">
        <thead>
          <tr>
            <th scope="col">Tip</th>
            <th scope="col">Datum placanja</th>
            <th scope="col">Vazi od</th>
   <th scope="col">Vazi do</th>
   <th scope="col">Cena</th>
   <th scope="col">Status aktivnosti</th>
   <th scope="col">Objekat</th>
            <th scope="col"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="m in memberships" >
            <td>{{m.type}}</td>
            <td>{{m.paidDate | dateFormat('DD.MM.YYYY.')}}</td>
            <td>{{m.validFrom | dateFormat('DD.MM.YYYY.')}}</td>
             <td>{{m.validTo | dateFormat('DD.MM.YYYY.')}}</td>
              <td>{{m.price}} din</td>
               <td>{{m.status}}</td>
            <td>{{m.placeName}}</td>
            <div>
            
            </div>
          </tr>
        </tbody>
    </table>
  </div>     
		                 
                    
           
		             
		    </div>          
</div>   
`,
methods:{



	

	
},
filters: {
    dateFormat: function(value, format){
        var parsed = moment(value);
        return parsed.format(format)
    }
},
mounted(){

      
   	 axios.get("/EliminacioniREST/rest/membership/myMemberships")
      .then( response => {
          this.memberships = response.data;
      
      })
      .catch(function(error){
          console.log(error)
      });


}


});