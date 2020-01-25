
const routes = [
  {
    path: '/',
    component: () => import('layouts/guest.vue'),
    children: [
      { path: '', component: () => import('pages/login.vue') }
    ]
  },
  {
    path: '/home',
    component: () => import('layouts/MyLayout.vue'),
    children: [
      { path: '', component: () => import('pages/Index.vue') }
    ]
  },
  {
    path: '/databuku',
    component: () => import('layouts/MyLayout.vue'),
    children: [
      { path: '', component: () => import('pages/databuku.vue') }
    ]
  },
  {
    path: '/input',
    component: () => import('layouts/MyLayout.vue'),
    children: [
      { path: '', component: () => import('pages/InputData.vue') }
    ]
  },
  {
    path: '/konfirmasipembelian',
    component: () => import('layouts/MyLayout.vue'),
    children: [
      { path: '', component: () => import('pages/konfirmasipembelian.vue') }
    ]
  },
  {
    path: '/order',
    component: () => import('layouts/MyLayout.vue'),
    children: [
      { path: '', component: () => import('pages/order.vue') }
    ]
  }
]
// Always leave this as last one

if (process.env.MODE !== 'ssr') {
  routes.push({
    path: '/inputdata',
    component: () => import('pages/Index.vue')
  })
}
export default routes
