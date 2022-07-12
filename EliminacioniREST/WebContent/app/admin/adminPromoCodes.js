Vue.component("admin-promoCodes", {
   
    data:function(){
        return{
           promoCode: {},
           mode: false
        }
    },
template: 
`
<div class="containerInfo"> 
 <div class="row content">
                <div class="col-sm-9">
                        <div class="informations" >
                            
                                    <table>
                                        <tr>
                                            <td> Oznaka: </td>
                                            <td> <input class="form-control" type="text" v-model="promoCode.id"/> </td>
                                        </tr>
                                        <tr> 
                                            <td> Koliko vazi dana: </td>
                                            <td> <input class="form-control" type="text"  v-model="promoCode.validDays"> </td>
                                        </tr>
                                        <tr> 
                                            <td> Koliko puta moze da se iskoristi :</td>
                                            <td> <input class="form-control" type="text"  v-model="promoCode.remainingUses"> </td>
                                        </tr>
                                        <tr> 
                                            <td> Mnozilac cene: </td>
                                            <td> <input class="form-control" type="text"  v-model="promoCode.discount"> </td>
                                        </tr>

										<button type="button" v-on:click="addPromoCode()"> Dodaj kod! </button>
                                    </table> 

                        </div>
                    </div>    
 </div>
   </div>
`,
methods:{
    addPromoCode: function(){
      axios.post("/EliminacioniREST/rest/promoCodes/addPromoCode", this.promoCode)
    },
},
mounted(){


},
filters: {
    dateFormat: function(value, format){
        var parsed = moment(value);
        return parsed.format(format)
    }
}
});