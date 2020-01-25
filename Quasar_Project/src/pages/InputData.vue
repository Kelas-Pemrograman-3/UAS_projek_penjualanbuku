<template>
    <q-page>
       <div class="row justify-center q-pt-md">
           <div class="col-md-6 col-xs-26">
                <q-card class="my-card">
                <q-card-section>
                    <div class="text-h4 q-pb-md"><center> Input Data Buku </center></div>
                    <q-form
      @submit="onSubmit"
      class="q-gutter-sm"
    >
       <q-input
        filled
        type="text"
        v-model="Id_Buku"
        label= "Masukkan Id Buku "
        lazy-rules
        :rules="[ val => val !== null && val !== '' || 'Wajib disi']"
      />

      <q-input
        filled
        type="text"
        v-model="Judul"
        label= "Masukkan Judul "
        lazy-rules
        :rules="[ val => val !== null && val !== '' || 'Wajib disi']"
      />

      <q-input
        filled
        type="text"
        v-model="Kategori"
        label= "Masukkan Kategori "
        lazy-rules
        :rules="[ val => val !== null && val !== '' || 'Wajib disi']"
      />

      <q-input
        filled
        type="text"
        v-model="Penerbit"
        label= "Masukkan Penerbit "
        lazy-rules
        :rules="[ val => val !== null && val !== '' || 'Wajib disi']"
      />

      <q-input
        filled
        type="text"
        v-model="Harga"
        label= "Masukkan Harga "
        lazy-rules
        :rules="[ val => val !== null && val !== '' || 'Wajib disi']"
      />

      <div>
        <q-btn label="SIMPAN" type="submit" color="primary"/>
      </div>
    </q-form>
                </q-card-section>
                </q-card>
           </div>
       </div>
    </q-page>
</template>

<script>
export default {
  data () {
    return {
      Id_Buku: '',
      Judul: '',
      Kategori: '',
      Penerbit: '',
      Harga: ''
    }
  },
  methods: {
    onSubmit () {
      this.$axios.post('databuku/input', {
        Id_Buku: this.Id_Buku,
        Judul: this.Judul,
        Kategori: this.Kategori,
        Penerbit: this.Penerbit,
        Harga: this.Harga
      }).then(res => {
        if (res.data.error) {
          this.showNotif(res.data.pesan, 'negative')
        } else {
          this.$router.push('/home')
        }
        this.Id_Buku = ''
        this.Judul = ''
        this.Kategori = ''
        this.Penerbit = ''
        this.Harga = ''
      })
    },
    showNotif (msg, color) {
      this.$q.notify({
        message: msg,
        color: 'color'
      })
    }
  }
}
</script>
