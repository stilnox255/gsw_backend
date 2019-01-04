Vue.component('is-part', {
    props: ['part'],
    template: `
    <article class="part">
        <h1>{{ part.name }}</h1>
        <ul>
          <li>Gewicht: {{ part.weight }}</li>
          <li>Preis: {{ part.price }} </li>
          <li>Datum: {{ part.freezeDate }}</li>
        </ul>
        <div class="contact">
          <a :href="part.mail" target="_new" class="mail"><i class="far fa-envelope fa-2x"></i></a>&nbsp;
          <a :href="part.whatsappLink" target="_new" class="whatsapp"><i class="fab fa-whatsapp fa-2x"></i></a>
          </div>
    </article>
    `
})

Vue.component('is-category', {
    props: ['category'],
    template: `
        <div class="category" :class="{ 'reh': category.isReh, 'ws': category.isWs , 'honey': category.isHoney  }">
            <h1>{{ category.name }} <span class="number">{{category.count}}</span> </h1>
            <div class="parts">
                <is-part v-for="part in category.parts" :key="part.id" :part="part" ></is-part>
            </div>
        </div>
        `
});

const cat = new Vue({
    el: '#categories',
    data: {
        categories: []
    },
    created: function () {
        const vm = this;
        let ref = new URLSearchParams(window.location.search).get('ref');
//        fetch('https://apps.ingoschindler.de:8443/wild/resources/users/' + encodeURIComponent(ref) + "/categories")
        fetch('http://localhost:8080/wild/resources/users/' + encodeURIComponent(ref) + "/categories")
            .then((r) => r.json())
            .then((j) => {
                vm.categories = j;
                vm.categories.forEach(c => {
//                    fetch('https://apps.ingoschindler.de:8443/wild/resources/users/' + encodeURIComponent(ref) + "/categories/" + encodeURIComponent(c.id) + "/parts/")
                    fetch('http://localhost:8080/wild/resources/users/' + encodeURIComponent(ref) + "/categories/" + encodeURIComponent(c.id) + "/parts/")
                        .then((r) => r.json())
                        .then((j) => {
                            Vue.set(c, 'parts', j)
                            c.count = j.length;
                            c.parts.forEach(part => {
                                part.whatsappLink = 'https://wa.me/4917634098326?text=' + encodeURIComponent(part.whatsapp);
                                part.mail = 'mailto:mail@ingoschindler.de?subject=' + encodeURIComponent("Wild: " + part.name) + "&body=" + encodeURIComponent(part.whatsapp);
                            })
                        });

                    c.isReh = false;
                    c.isWs = false;
                    c.isHoney = false;

                    switch (c.name) {
                        case 'Reh':
                            c.isReh = true;
                            break;
                        case 'Wildschwein':
                            c.isWs = true;
                            break;
                        case 'Honig':
                            c.isHoney = true;
                            break;
                    }
                });
            });
    }
});