class RequestMan {
    constructor(parent) {
        this.parent = parent;
        this.id = 'RMN' + uuid();
        console.info("Request manager:    :" + this.id);
        return this;
    }
    setParent() {
        this.parent = parent;
        //method chaining
        return this;
    }
    getParent() {
        return this.parent;
    }
    buildPost = (blogs) => {
        let panels = [];
        //console.log(blogs);
        for (let index = 0; index < blogs.length; index++) {

            let post = new Post(this.parent)
                .setImg(blogs[index].titleImage)
                .setheadLine(blogs[index].title)
                .setClassList("row m-0 w-100")
                .setAuthor(blogs[index].owner.name)
                .setDate(blogs[index].date)
                .setHash(blogs[index].hash)
                .setTags(blogs[index].tags)
                .setCategory(blogs[index].category)
                .setRemote(blogs[index].hash)
                .setBody(blogs[index].body.hexDecode());
                // .toggleSize();

            //console.info(this.parent);
            panels.push(post)
        }
        return panels;
    }

    sendPost(action, data) {
        console.info('Saving Blog Post');
        fetch('http://localhost:8080/blog/' + action, {
                method: 'post',
                headers: {
                    "Content-type": "application/json",
                    "head": data["head"],
                    "author": data["author"],
                    "aDate": getCurrentDate(),
                    "tags": data["tags"],
                    "category": data["category"],
                    "hash": data["hash"]
                    //,
                    //"auth": this.parent.getAdminToken
                },
                body: data["body"]
            })
            .then(response => Promise.all([response, response.json()]))
            .then(([response, json]) => {
                if (!response.ok) {
                    throw new Error(json.message);
                }
            })
            .catch(exception => {
                var errorMap = new Map([
                    [TypeError, "There was a problem fetching the response."],
                    [SyntaxError, "There was a problem parsing the response."],
                    [Error, exception.message]
                ]).get(exception.constructor);
            });
        return this;
    }

    fetchBlogs(filter = '') {
        console.info("Request Filter      :" + filter);
        fetch(`http://localhost:8080/blog/all?filter=${filter}`)
            .then(function (response) {
                return response.json();
            }).then((myJson) => {
                // let blogs = JSON.stringify(myJson)
                console.log("filtered response from server:");
                console.log(myJson);
                this.parent.removeAllPanels();
                this.parent.addPanels(this.buildPost(myJson))
                this.parent.getNav().searchFocus();
            });
        return this;
    }
}