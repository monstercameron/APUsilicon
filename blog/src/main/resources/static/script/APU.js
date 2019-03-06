class APU {
    constructor(parent) {
        this.parent = parent;
        this.id = 'APU' + uuid();
        this.dict = {};
        this.fieldsList = ["Brand", "Family", "SKU", "Cores", "Threads", "Shaders", "Link"];
        this.fieldsDict = {};
        return this;
    }
    setbrand(brand) {
        this.brand = brand
        return this;
    }
    getbrand() {
        return this.brand;
    }
    setfamily(family) {
        this.family = family;
        return this;
    }
    getfamily() {
        return this.family;
    }
    setsku(sku) {
        this.sku = sku;
        return this;
    }
    getsku() {
        return this.sku;
    }
    setcores(cores) {
        this.cores = cores;
        return this;
    }
    getcores() {
        return this.cores;
    }
    setthreads(threads) {
        this.threads = threads;
        return this;
    }
    getthreads() {
        return this.threads;
    }
    setshaders(shaders) {
        this.shaders = shaders;
        return this;
    }
    getshaders() {
        return this.shaders;
    }
    setlink(link) {
        this.link = link;
        return this;
    }
    getlink() {
        return this.link;
    }
    build(){
        this.dict['brand'] = document.querySelector('#'+this.fieldsDict['Brand']).value;
        this.dict['family'] = document.querySelector('#'+this.fieldsDict['Family']).value;
        this.dict['sku'] = document.querySelector('#'+this.fieldsDict['SKU']).value;
        this.dict['cores'] = document.querySelector('#'+this.fieldsDict['Cores']).value;
        this.dict['threads'] = document.querySelector('#'+this.fieldsDict['Threads']).value;
        this.dict['shaders'] = document.querySelector('#'+this.fieldsDict['Shaders']).value;
        this.dict['link'] = document.querySelector('#'+this.fieldsDict['Link']).value;
        console.log(this.dict);
        return this.dict;
    }
    template() {
        let container = document.createElement('div');
        container.id=this.id;
        container.classList.add('row');
        container.classList.add('m-0');
        container.classList.add('w-100');

        for (let index = 0; index < this.fieldsList.length; index++) {
            this.fieldsDict[this.fieldsList[index]] = 'FLD'+uuid();
        }

        container.innerHTML = `
        <div class="col-sm-8 mx-auto border rounded shadow m-2 p-2">
          <!-- window action -->
          <div class="col-sm-12 p-0">
              <div class="col-sm-auto ml-auto p-1">
                <button 
                    class="btn btn-block" 
                    style="max-height: 24px;"
                    onclick="page.setView()"
                >
                  <i class="material-icons"> expand_more </i>
                </button>
              </div>
            </div>
          <!-- title -->
          <div class="text-center mt-2">
            <h2>
              Add APU
            </h2>
            <!-- fields -->
            ${
                this.fieldsList.map(field => {
                    return `
                    <input 
                        type="text" 
                        class="form-control mt-1 mb-1" 
                        placeholder="${field}" 
                        id="${this.fieldsDict[field]}"
                    >`
                }).join("")
            }
            <!-- send button -->
            <button 
                class="btn btn-primary btn-block"
                onclick="page.getAPUMan().sendAPU()"
            >
                Add APU
            </button>
          </div>
        </div>`
        return container;
    }
    print() {
        console.log(this);
    }
}