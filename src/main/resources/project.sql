PGDMP                         z            movies    14.5    15.0 k    }           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            ~           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false                       0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    19137    movies    DATABASE     h   CREATE DATABASE movies WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'C';
    DROP DATABASE movies;
                postgres    false                        2615    2200    public    SCHEMA     2   -- *not* creating schema, since initdb creates it
 2   -- *not* dropping schema, since initdb creates it
                postgres    false            �           0    0    SCHEMA public    ACL     Q   REVOKE USAGE ON SCHEMA public FROM PUBLIC;
GRANT ALL ON SCHEMA public TO PUBLIC;
                   postgres    false    5            �            1259    20150    category    TABLE        CREATE TABLE public.category (
    id bigint NOT NULL,
    created_at timestamp without time zone,
    updated_at timestamp without time zone,
    created_by bigint,
    updated_by bigint,
    enable boolean NOT NULL,
    name character varying(255),
    search character varying(255)
);
    DROP TABLE public.category;
       public         heap    postgres    false    5            �            1259    20155    category_id_seq    SEQUENCE     x   CREATE SEQUENCE public.category_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public.category_id_seq;
       public          postgres    false    5    210            �           0    0    category_id_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE public.category_id_seq OWNED BY public.category.id;
          public          postgres    false    211            �            1259    20156    comment    TABLE     �   CREATE TABLE public.comment (
    id bigint NOT NULL,
    created_at timestamp without time zone,
    updated_at timestamp without time zone,
    comment character varying(255),
    product_id bigint,
    user_id bigint
);
    DROP TABLE public.comment;
       public         heap    postgres    false    5            �            1259    20159    comment_id_seq    SEQUENCE     w   CREATE SEQUENCE public.comment_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE public.comment_id_seq;
       public          postgres    false    5    212            �           0    0    comment_id_seq    SEQUENCE OWNED BY     A   ALTER SEQUENCE public.comment_id_seq OWNED BY public.comment.id;
          public          postgres    false    213            �            1259    20160    country    TABLE     �   CREATE TABLE public.country (
    id bigint NOT NULL,
    created_at timestamp without time zone,
    updated_at timestamp without time zone,
    created_by bigint,
    updated_by bigint,
    enable boolean NOT NULL,
    name character varying(255)
);
    DROP TABLE public.country;
       public         heap    postgres    false    5            �            1259    20163    country_id_seq    SEQUENCE     w   CREATE SEQUENCE public.country_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE public.country_id_seq;
       public          postgres    false    5    214            �           0    0    country_id_seq    SEQUENCE OWNED BY     A   ALTER SEQUENCE public.country_id_seq OWNED BY public.country.id;
          public          postgres    false    215            �            1259    19267    hibernate_sequence    SEQUENCE     {   CREATE SEQUENCE public.hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.hibernate_sequence;
       public          postgres    false    5            �            1259    20164    image    TABLE     S  CREATE TABLE public.image (
    id bigint NOT NULL,
    created_at timestamp without time zone,
    updated_at timestamp without time zone,
    created_by bigint,
    updated_by bigint,
    priority integer NOT NULL,
    url_medium character varying(255),
    url_small character varying(255),
    product_id bigint,
    shop_id bigint
);
    DROP TABLE public.image;
       public         heap    postgres    false    5            �            1259    20169    image_id_seq    SEQUENCE     u   CREATE SEQUENCE public.image_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.image_id_seq;
       public          postgres    false    216    5            �           0    0    image_id_seq    SEQUENCE OWNED BY     =   ALTER SEQUENCE public.image_id_seq OWNED BY public.image.id;
          public          postgres    false    217            �            1259    20170 
   prefecture    TABLE     �   CREATE TABLE public.prefecture (
    id bigint NOT NULL,
    created_at timestamp without time zone,
    updated_at timestamp without time zone,
    name character varying(255)
);
    DROP TABLE public.prefecture;
       public         heap    postgres    false    5            �            1259    20173    prefecture_id_seq    SEQUENCE     z   CREATE SEQUENCE public.prefecture_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 (   DROP SEQUENCE public.prefecture_id_seq;
       public          postgres    false    218    5            �           0    0    prefecture_id_seq    SEQUENCE OWNED BY     G   ALTER SEQUENCE public.prefecture_id_seq OWNED BY public.prefecture.id;
          public          postgres    false    219            �            1259    20174    product    TABLE     W  CREATE TABLE public.product (
    id bigint NOT NULL,
    created_at timestamp without time zone,
    updated_at timestamp without time zone,
    created_by bigint,
    updated_by bigint,
    description text,
    description_sort character varying(255),
    enable boolean NOT NULL,
    hot boolean NOT NULL,
    name character varying(255),
    rating integer NOT NULL,
    tiktok character varying(255),
    country_id bigint,
    shop_id bigint,
    sub_category_id bigint,
    featured bigint,
    until timestamp without time zone,
    price bigint,
    sale_price bigint,
    sale boolean
);
    DROP TABLE public.product;
       public         heap    postgres    false    5            �            1259    20179    product_id_seq    SEQUENCE     w   CREATE SEQUENCE public.product_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE public.product_id_seq;
       public          postgres    false    5    220            �           0    0    product_id_seq    SEQUENCE OWNED BY     A   ALTER SEQUENCE public.product_id_seq OWNED BY public.product.id;
          public          postgres    false    221            �            1259    20180    product_tags    TABLE     c   CREATE TABLE public.product_tags (
    products_id bigint NOT NULL,
    tags_id bigint NOT NULL
);
     DROP TABLE public.product_tags;
       public         heap    postgres    false    5            �            1259    20183    roles    TABLE     V   CREATE TABLE public.roles (
    id bigint NOT NULL,
    name character varying(60)
);
    DROP TABLE public.roles;
       public         heap    postgres    false    5            �            1259    20186    roles_id_seq    SEQUENCE     u   CREATE SEQUENCE public.roles_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.roles_id_seq;
       public          postgres    false    5    223            �           0    0    roles_id_seq    SEQUENCE OWNED BY     =   ALTER SEQUENCE public.roles_id_seq OWNED BY public.roles.id;
          public          postgres    false    224            �            1259    20187    shop    TABLE     �  CREATE TABLE public.shop (
    id bigint NOT NULL,
    created_at timestamp without time zone,
    updated_at timestamp without time zone,
    created_by bigint,
    updated_by bigint,
    active boolean NOT NULL,
    address character varying(255),
    email character varying(255),
    name character varying(255),
    number character varying(255),
    user_id bigint,
    telegram_group character varying(255),
    telegram_id character varying(255)
);
    DROP TABLE public.shop;
       public         heap    postgres    false    5            �            1259    20192    shop_id_seq    SEQUENCE     t   CREATE SEQUENCE public.shop_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 "   DROP SEQUENCE public.shop_id_seq;
       public          postgres    false    225    5            �           0    0    shop_id_seq    SEQUENCE OWNED BY     ;   ALTER SEQUENCE public.shop_id_seq OWNED BY public.shop.id;
          public          postgres    false    226            �            1259    20193    sub_category    TABLE     E  CREATE TABLE public.sub_category (
    id bigint NOT NULL,
    created_at timestamp without time zone,
    updated_at timestamp without time zone,
    created_by bigint,
    updated_by bigint,
    enable boolean NOT NULL,
    name character varying(255),
    category_id bigint NOT NULL,
    search character varying(255)
);
     DROP TABLE public.sub_category;
       public         heap    postgres    false    5            �            1259    20198    sub_category_id_seq    SEQUENCE     |   CREATE SEQUENCE public.sub_category_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE public.sub_category_id_seq;
       public          postgres    false    227    5            �           0    0    sub_category_id_seq    SEQUENCE OWNED BY     K   ALTER SEQUENCE public.sub_category_id_seq OWNED BY public.sub_category.id;
          public          postgres    false    228            �            1259    20199    tag    TABLE     �   CREATE TABLE public.tag (
    id bigint NOT NULL,
    created_at timestamp without time zone,
    updated_at timestamp without time zone,
    created_by bigint,
    updated_by bigint,
    enable boolean NOT NULL,
    name character varying(255)
);
    DROP TABLE public.tag;
       public         heap    postgres    false    5            �            1259    20202 
   tag_id_seq    SEQUENCE     s   CREATE SEQUENCE public.tag_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 !   DROP SEQUENCE public.tag_id_seq;
       public          postgres    false    229    5            �           0    0 
   tag_id_seq    SEQUENCE OWNED BY     9   ALTER SEQUENCE public.tag_id_seq OWNED BY public.tag.id;
          public          postgres    false    230            �            1259    20203 
   user_roles    TABLE     ]   CREATE TABLE public.user_roles (
    user_id bigint NOT NULL,
    role_id bigint NOT NULL
);
    DROP TABLE public.user_roles;
       public         heap    postgres    false    5            �            1259    20206    users    TABLE       CREATE TABLE public.users (
    id bigint NOT NULL,
    created_at timestamp without time zone,
    updated_at timestamp without time zone,
    address character varying(255),
    avatar character varying(255),
    birthday timestamp without time zone,
    email character varying(40),
    is_account_non_expired boolean NOT NULL,
    is_account_non_locked boolean NOT NULL,
    is_credentials_non_expired boolean NOT NULL,
    is_enabled boolean NOT NULL,
    mobile character varying(255),
    password character varying(100),
    prefecture_id bigint,
    create_expiry timestamp without time zone,
    create_token character varying(255),
    reset_expiry timestamp without time zone,
    reset_token character varying(255),
    username character varying(255),
    shop_id bigint
);
    DROP TABLE public.users;
       public         heap    postgres    false    5            �            1259    20211    users_id_seq    SEQUENCE     u   CREATE SEQUENCE public.users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.users_id_seq;
       public          postgres    false    5    232            �           0    0    users_id_seq    SEQUENCE OWNED BY     =   ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id;
          public          postgres    false    233            �           2604    20212    category id    DEFAULT     j   ALTER TABLE ONLY public.category ALTER COLUMN id SET DEFAULT nextval('public.category_id_seq'::regclass);
 :   ALTER TABLE public.category ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    211    210            �           2604    20213 
   comment id    DEFAULT     h   ALTER TABLE ONLY public.comment ALTER COLUMN id SET DEFAULT nextval('public.comment_id_seq'::regclass);
 9   ALTER TABLE public.comment ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    213    212            �           2604    20214 
   country id    DEFAULT     h   ALTER TABLE ONLY public.country ALTER COLUMN id SET DEFAULT nextval('public.country_id_seq'::regclass);
 9   ALTER TABLE public.country ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    215    214            �           2604    20215    image id    DEFAULT     d   ALTER TABLE ONLY public.image ALTER COLUMN id SET DEFAULT nextval('public.image_id_seq'::regclass);
 7   ALTER TABLE public.image ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    217    216            �           2604    20216    prefecture id    DEFAULT     n   ALTER TABLE ONLY public.prefecture ALTER COLUMN id SET DEFAULT nextval('public.prefecture_id_seq'::regclass);
 <   ALTER TABLE public.prefecture ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    219    218            �           2604    20217 
   product id    DEFAULT     h   ALTER TABLE ONLY public.product ALTER COLUMN id SET DEFAULT nextval('public.product_id_seq'::regclass);
 9   ALTER TABLE public.product ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    221    220            �           2604    20218    roles id    DEFAULT     d   ALTER TABLE ONLY public.roles ALTER COLUMN id SET DEFAULT nextval('public.roles_id_seq'::regclass);
 7   ALTER TABLE public.roles ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    224    223            �           2604    20219    shop id    DEFAULT     b   ALTER TABLE ONLY public.shop ALTER COLUMN id SET DEFAULT nextval('public.shop_id_seq'::regclass);
 6   ALTER TABLE public.shop ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    226    225            �           2604    20220    sub_category id    DEFAULT     r   ALTER TABLE ONLY public.sub_category ALTER COLUMN id SET DEFAULT nextval('public.sub_category_id_seq'::regclass);
 >   ALTER TABLE public.sub_category ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    228    227            �           2604    20221    tag id    DEFAULT     `   ALTER TABLE ONLY public.tag ALTER COLUMN id SET DEFAULT nextval('public.tag_id_seq'::regclass);
 5   ALTER TABLE public.tag ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    230    229            �           2604    20222    users id    DEFAULT     d   ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq'::regclass);
 7   ALTER TABLE public.users ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    233    232            c          0    20150    category 
   TABLE DATA           l   COPY public.category (id, created_at, updated_at, created_by, updated_by, enable, name, search) FROM stdin;
    public          postgres    false    210   �       e          0    20156    comment 
   TABLE DATA           [   COPY public.comment (id, created_at, updated_at, comment, product_id, user_id) FROM stdin;
    public          postgres    false    212   '�       g          0    20160    country 
   TABLE DATA           c   COPY public.country (id, created_at, updated_at, created_by, updated_by, enable, name) FROM stdin;
    public          postgres    false    214   ��       i          0    20164    image 
   TABLE DATA           �   COPY public.image (id, created_at, updated_at, created_by, updated_by, priority, url_medium, url_small, product_id, shop_id) FROM stdin;
    public          postgres    false    216   �       k          0    20170 
   prefecture 
   TABLE DATA           F   COPY public.prefecture (id, created_at, updated_at, name) FROM stdin;
    public          postgres    false    218   N�       m          0    20174    product 
   TABLE DATA           �   COPY public.product (id, created_at, updated_at, created_by, updated_by, description, description_sort, enable, hot, name, rating, tiktok, country_id, shop_id, sub_category_id, featured, until, price, sale_price, sale) FROM stdin;
    public          postgres    false    220    �       o          0    20180    product_tags 
   TABLE DATA           <   COPY public.product_tags (products_id, tags_id) FROM stdin;
    public          postgres    false    222   g�       p          0    20183    roles 
   TABLE DATA           )   COPY public.roles (id, name) FROM stdin;
    public          postgres    false    223   ��       r          0    20187    shop 
   TABLE DATA           �   COPY public.shop (id, created_at, updated_at, created_by, updated_by, active, address, email, name, number, user_id, telegram_group, telegram_id) FROM stdin;
    public          postgres    false    225   ȕ       t          0    20193    sub_category 
   TABLE DATA           }   COPY public.sub_category (id, created_at, updated_at, created_by, updated_by, enable, name, category_id, search) FROM stdin;
    public          postgres    false    227   L�       v          0    20199    tag 
   TABLE DATA           _   COPY public.tag (id, created_at, updated_at, created_by, updated_by, enable, name) FROM stdin;
    public          postgres    false    229   $�       x          0    20203 
   user_roles 
   TABLE DATA           6   COPY public.user_roles (user_id, role_id) FROM stdin;
    public          postgres    false    231   A�       y          0    20206    users 
   TABLE DATA              COPY public.users (id, created_at, updated_at, address, avatar, birthday, email, is_account_non_expired, is_account_non_locked, is_credentials_non_expired, is_enabled, mobile, password, prefecture_id, create_expiry, create_token, reset_expiry, reset_token, username, shop_id) FROM stdin;
    public          postgres    false    232   j�       �           0    0    category_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.category_id_seq', 53, true);
          public          postgres    false    211            �           0    0    comment_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.comment_id_seq', 1, false);
          public          postgres    false    213            �           0    0    country_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.country_id_seq', 99, true);
          public          postgres    false    215            �           0    0    hibernate_sequence    SEQUENCE SET     C   SELECT pg_catalog.setval('public.hibernate_sequence', 1197, true);
          public          postgres    false    209            �           0    0    image_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.image_id_seq', 19, true);
          public          postgres    false    217            �           0    0    prefecture_id_seq    SEQUENCE SET     @   SELECT pg_catalog.setval('public.prefecture_id_seq', 63, true);
          public          postgres    false    219            �           0    0    product_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.product_id_seq', 1, false);
          public          postgres    false    221            �           0    0    roles_id_seq    SEQUENCE SET     :   SELECT pg_catalog.setval('public.roles_id_seq', 4, true);
          public          postgres    false    224            �           0    0    shop_id_seq    SEQUENCE SET     9   SELECT pg_catalog.setval('public.shop_id_seq', 1, true);
          public          postgres    false    226            �           0    0    sub_category_id_seq    SEQUENCE SET     D   SELECT pg_catalog.setval('public.sub_category_id_seq', 1421, true);
          public          postgres    false    228            �           0    0 
   tag_id_seq    SEQUENCE SET     9   SELECT pg_catalog.setval('public.tag_id_seq', 1, false);
          public          postgres    false    230            �           0    0    users_id_seq    SEQUENCE SET     :   SELECT pg_catalog.setval('public.users_id_seq', 6, true);
          public          postgres    false    233            �           2606    20225 !   users UK6j5t70rd2eub907qysjvvd76n 
   CONSTRAINT     _   ALTER TABLE ONLY public.users
    ADD CONSTRAINT "UK6j5t70rd2eub907qysjvvd76n" UNIQUE (email);
 M   ALTER TABLE ONLY public.users DROP CONSTRAINT "UK6j5t70rd2eub907qysjvvd76n";
       public            postgres    false    232            �           2606    20227    category category_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.category
    ADD CONSTRAINT category_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.category DROP CONSTRAINT category_pkey;
       public            postgres    false    210            �           2606    20229    comment comment_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.comment
    ADD CONSTRAINT comment_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.comment DROP CONSTRAINT comment_pkey;
       public            postgres    false    212            �           2606    20231    country country_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.country
    ADD CONSTRAINT country_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.country DROP CONSTRAINT country_pkey;
       public            postgres    false    214            �           2606    20233    image image_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.image
    ADD CONSTRAINT image_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.image DROP CONSTRAINT image_pkey;
       public            postgres    false    216            �           2606    20235    prefecture prefecture_pkey 
   CONSTRAINT     X   ALTER TABLE ONLY public.prefecture
    ADD CONSTRAINT prefecture_pkey PRIMARY KEY (id);
 D   ALTER TABLE ONLY public.prefecture DROP CONSTRAINT prefecture_pkey;
       public            postgres    false    218            �           2606    20237    product product_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.product
    ADD CONSTRAINT product_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.product DROP CONSTRAINT product_pkey;
       public            postgres    false    220            �           2606    20239    roles roles_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.roles
    ADD CONSTRAINT roles_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.roles DROP CONSTRAINT roles_pkey;
       public            postgres    false    223            �           2606    20241    shop shop_pkey 
   CONSTRAINT     L   ALTER TABLE ONLY public.shop
    ADD CONSTRAINT shop_pkey PRIMARY KEY (id);
 8   ALTER TABLE ONLY public.shop DROP CONSTRAINT shop_pkey;
       public            postgres    false    225            �           2606    20243    sub_category sub_category_pkey 
   CONSTRAINT     \   ALTER TABLE ONLY public.sub_category
    ADD CONSTRAINT sub_category_pkey PRIMARY KEY (id);
 H   ALTER TABLE ONLY public.sub_category DROP CONSTRAINT sub_category_pkey;
       public            postgres    false    227            �           2606    20245    tag tag_pkey 
   CONSTRAINT     J   ALTER TABLE ONLY public.tag
    ADD CONSTRAINT tag_pkey PRIMARY KEY (id);
 6   ALTER TABLE ONLY public.tag DROP CONSTRAINT tag_pkey;
       public            postgres    false    229            �           2606    20247 "   roles uk_nb4h0p6txrmfc0xbrd1kglp9t 
   CONSTRAINT     ]   ALTER TABLE ONLY public.roles
    ADD CONSTRAINT uk_nb4h0p6txrmfc0xbrd1kglp9t UNIQUE (name);
 L   ALTER TABLE ONLY public.roles DROP CONSTRAINT uk_nb4h0p6txrmfc0xbrd1kglp9t;
       public            postgres    false    223            �           2606    20249    user_roles user_roles_pkey 
   CONSTRAINT     f   ALTER TABLE ONLY public.user_roles
    ADD CONSTRAINT user_roles_pkey PRIMARY KEY (user_id, role_id);
 D   ALTER TABLE ONLY public.user_roles DROP CONSTRAINT user_roles_pkey;
       public            postgres    false    231    231            �           2606    20251    users users_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.users DROP CONSTRAINT users_pkey;
       public            postgres    false    232            �           2606    20252     shop FK36nknc2cfkui55nrprhr55d5v    FK CONSTRAINT     �   ALTER TABLE ONLY public.shop
    ADD CONSTRAINT "FK36nknc2cfkui55nrprhr55d5v" FOREIGN KEY (user_id) REFERENCES public.users(id);
 L   ALTER TABLE ONLY public.shop DROP CONSTRAINT "FK36nknc2cfkui55nrprhr55d5v";
       public          postgres    false    225    232    3527            �           2606    20257 (   product_tags FK4n67l5ht7b0whs4sragr8axph    FK CONSTRAINT     �   ALTER TABLE ONLY public.product_tags
    ADD CONSTRAINT "FK4n67l5ht7b0whs4sragr8axph" FOREIGN KEY (products_id) REFERENCES public.product(id);
 T   ALTER TABLE ONLY public.product_tags DROP CONSTRAINT "FK4n67l5ht7b0whs4sragr8axph";
       public          postgres    false    222    220    3511            �           2606    20262 !   users FK4qu2c5f8i7hc6vf050wb0k948    FK CONSTRAINT     �   ALTER TABLE ONLY public.users
    ADD CONSTRAINT "FK4qu2c5f8i7hc6vf050wb0k948" FOREIGN KEY (shop_id) REFERENCES public.shop(id);
 M   ALTER TABLE ONLY public.users DROP CONSTRAINT "FK4qu2c5f8i7hc6vf050wb0k948";
       public          postgres    false    232    3517    225            �           2606    20267 #   product FK6cighpvj59jngocmr7ipusavu    FK CONSTRAINT     �   ALTER TABLE ONLY public.product
    ADD CONSTRAINT "FK6cighpvj59jngocmr7ipusavu" FOREIGN KEY (sub_category_id) REFERENCES public.sub_category(id);
 O   ALTER TABLE ONLY public.product DROP CONSTRAINT "FK6cighpvj59jngocmr7ipusavu";
       public          postgres    false    220    3519    227            �           2606    20272 &   user_roles FK7ov27fyo7ebsvada1ej7qkphl    FK CONSTRAINT     �   ALTER TABLE ONLY public.user_roles
    ADD CONSTRAINT "FK7ov27fyo7ebsvada1ej7qkphl" FOREIGN KEY (user_id) REFERENCES public.users(id);
 R   ALTER TABLE ONLY public.user_roles DROP CONSTRAINT "FK7ov27fyo7ebsvada1ej7qkphl";
       public          postgres    false    231    232    3527            �           2606    20277 #   product FKaqw8hq5gyp5v7513xkvdac0lh    FK CONSTRAINT     �   ALTER TABLE ONLY public.product
    ADD CONSTRAINT "FKaqw8hq5gyp5v7513xkvdac0lh" FOREIGN KEY (country_id) REFERENCES public.country(id);
 O   ALTER TABLE ONLY public.product DROP CONSTRAINT "FKaqw8hq5gyp5v7513xkvdac0lh";
       public          postgres    false    220    214    3505            �           2606    20282 !   image FKd0g1tmjbdkqd13kkeo6d3tcep    FK CONSTRAINT     �   ALTER TABLE ONLY public.image
    ADD CONSTRAINT "FKd0g1tmjbdkqd13kkeo6d3tcep" FOREIGN KEY (shop_id) REFERENCES public.shop(id);
 M   ALTER TABLE ONLY public.image DROP CONSTRAINT "FKd0g1tmjbdkqd13kkeo6d3tcep";
       public          postgres    false    225    3517    216            �           2606    20287 &   user_roles FKej3jidxlte0r8flpavhiso3g6    FK CONSTRAINT     �   ALTER TABLE ONLY public.user_roles
    ADD CONSTRAINT "FKej3jidxlte0r8flpavhiso3g6" FOREIGN KEY (role_id) REFERENCES public.roles(id);
 R   ALTER TABLE ONLY public.user_roles DROP CONSTRAINT "FKej3jidxlte0r8flpavhiso3g6";
       public          postgres    false    223    231    3513            �           2606    20292 #   product FKett17tt7p4pbuitp0if89kdt5    FK CONSTRAINT     �   ALTER TABLE ONLY public.product
    ADD CONSTRAINT "FKett17tt7p4pbuitp0if89kdt5" FOREIGN KEY (shop_id) REFERENCES public.shop(id);
 O   ALTER TABLE ONLY public.product DROP CONSTRAINT "FKett17tt7p4pbuitp0if89kdt5";
       public          postgres    false    220    225    3517            �           2606    20297 !   image FKf34pcxeeyw3it5v2rthteef7v    FK CONSTRAINT     �   ALTER TABLE ONLY public.image
    ADD CONSTRAINT "FKf34pcxeeyw3it5v2rthteef7v" FOREIGN KEY (product_id) REFERENCES public.product(id);
 M   ALTER TABLE ONLY public.image DROP CONSTRAINT "FKf34pcxeeyw3it5v2rthteef7v";
       public          postgres    false    220    216    3511            �           2606    20302 !   users FKhf381xrw2kuxkx75jyl8pewp4    FK CONSTRAINT     �   ALTER TABLE ONLY public.users
    ADD CONSTRAINT "FKhf381xrw2kuxkx75jyl8pewp4" FOREIGN KEY (prefecture_id) REFERENCES public.prefecture(id);
 M   ALTER TABLE ONLY public.users DROP CONSTRAINT "FKhf381xrw2kuxkx75jyl8pewp4";
       public          postgres    false    232    218    3509            �           2606    20307 #   comment FKkwd7w0meugs0nu95w48hqhw2w    FK CONSTRAINT     �   ALTER TABLE ONLY public.comment
    ADD CONSTRAINT "FKkwd7w0meugs0nu95w48hqhw2w" FOREIGN KEY (user_id) REFERENCES public.users(id);
 O   ALTER TABLE ONLY public.comment DROP CONSTRAINT "FKkwd7w0meugs0nu95w48hqhw2w";
       public          postgres    false    3527    212    232            �           2606    20312 #   comment FKlo59icyee0u6jucfb68x22o3v    FK CONSTRAINT     �   ALTER TABLE ONLY public.comment
    ADD CONSTRAINT "FKlo59icyee0u6jucfb68x22o3v" FOREIGN KEY (product_id) REFERENCES public.product(id);
 O   ALTER TABLE ONLY public.comment DROP CONSTRAINT "FKlo59icyee0u6jucfb68x22o3v";
       public          postgres    false    212    3511    220            �           2606    20317 (   product_tags FKmb0hq4cm6sa2ji8a1ymqfyax4    FK CONSTRAINT     �   ALTER TABLE ONLY public.product_tags
    ADD CONSTRAINT "FKmb0hq4cm6sa2ji8a1ymqfyax4" FOREIGN KEY (tags_id) REFERENCES public.tag(id);
 T   ALTER TABLE ONLY public.product_tags DROP CONSTRAINT "FKmb0hq4cm6sa2ji8a1ymqfyax4";
       public          postgres    false    222    229    3521            �           2606    20322 (   sub_category FKshci6pjlkmq0u7m9m1arwqbpb    FK CONSTRAINT     �   ALTER TABLE ONLY public.sub_category
    ADD CONSTRAINT "FKshci6pjlkmq0u7m9m1arwqbpb" FOREIGN KEY (category_id) REFERENCES public.category(id);
 T   ALTER TABLE ONLY public.sub_category DROP CONSTRAINT "FKshci6pjlkmq0u7m9m1arwqbpb";
       public          postgres    false    3501    227    210            c     x�3�4202�54�52Q04�2��26�3273�0�'e�i�!w��T)J�KW�K���M��u��/�H-�MN,�2�4��\������6��^����f�1E6�>ܵSAM���JN���b]�̔b]�ĤL��&� t�R�̇����/��$������%gd���:&'����5�������'+� �\�SjbiI%�T3
M9�;#��ݜ��9%`S��Lu�<��R������ȇ��e)��L���k��vJL����� �ʘ      e   c   x�3�4202�54�52Q04�2��26�3273�0�'���P����P�_�����99��(50%1/]!=3Q!%�(�L��k�eL����p���qqq ��4�      g   ^   x�3�4202�54�5�T04�2��22�3�0645�'e�%�
a�w��(�%�rQf�_��]kK��Z�ǅWa�</�S,}�{b2W� c�40      i   6  x����N�0�kx���������"����d��c8 ��ޒ�����W�I�/M�*�pJ5N�@Tf�<��q���^�e��~�C6N�%h����N���9<E�xi�U�w�*���׍#�!b4@����y��u| {t�؛yQ�̖��ͩ�uI�Y�#�*���ڥ���o\	�K�	�@κ$ݬϐV�!�Cn&n�-a�/d3���u���7�'�3��m�/w��$�L����Q7lb�����p>�P�a��]�����b�]=f+��`�`J�����+�m�!`����?��b      k   �  x�}�MS�8�ϫ_�۞ƥO[���LA�e�j���$"va,�P��ӑ�sy1קӖ����ɥ�"��V"�*�WUd&�"�K�{lw��z�c�.��j	�;����*�V�K��w�j���<�вN���w�P?��*�3�-s�U���I.^��.�Om���%��k��8�%ßs�,q�&��$�ulC�WM�Y���'���m���}xل�9`j3^P ���﫻��Wa<���ZX��Ԍ���m��q�������K��sߑ��'�
$`�i.��7��Y�ZR�ҁi!��:�C7��@�چmI��0c�THW3��\���m��@R�T9�Ҳ�nbh�(HL�Y�������Ǧ	�&�q�S�qB����1����mW���.��]�6잆�P\&��EkF�a3�N"��L�\��]W��*��2<��(U��o��B�(.��)X�3������-�t�ŌR��g���I�Y��R�Pl3�?^du߇w��t+�
�ֲ�g�PjW߆���HW.��)جftlVg$ޞ"�dK���O�8���%)f�$�sT���#I���n7:�՜�+79��	�8��7MjO
(�LE�4���l�K��S@�d')�|�:^�c�*R��*�j�9�ۡ�^�U��Hv:WV���P��]�l�֧�*�]27&/�.�P�&�0��X��JEt�h�Y��<��p�Y|�	��=����m�,�����gr0���Fmd	�3���39A��V�&uB)4��f�$��|mV;t�]������*��HE&]A;�:����}�3��+2�
��:�����0rqM�#Gٟ��j�Q&����~B7U[���o�"�� ���@c�Rrn�aꚡ%�L�`B�f{^�U�4Ρu�	��Lh��my��m�I��!��Lma5�htv_��
L�d�LJm`Üѣ�5��H�2S4�,v?�_���.4]Y���݂�5w����:���>��N:�Bi�/��3��]�9n�3#{M�:R��
z���5�K
H[mc�!��%i�茾�m�ڛA�%3���'FtC35!�� ���Gc$�x���3H���Wj��fj���ABRiu��bF�8T��xv�H�*��lB����ߪI��Ti����h�k�w1���j��ѵ��x�al�r�2SP�����X4�o����1�k{�      m   W  x��Y[��~^~�A$2��^��������YAMw�t�tW5]�;;y��EQ��(�!Q�R��R�]Ey�b�K��������ֻ��:��ܺW����KK���tau��Օ���Ko�\���GK�'%����A����k}FV�H(2�t�d����Ba��YE�pq���_������������t?ܩ�װq�ad&��m4=�?a�
���J������������Q'���o'��A���N�f��gAD�VV�7H����S����O2î~7���A��EZ=�eWw����5�Ȥ$
&_��d�(_�R����N6=�;�gBG���Xp'b�)<S��ծ��Iɿ�;o]�fcz🀆Ӄ�9��OR6F>ȧѵ�.��ϙj��̀m%��n��������+���W��/�W{6��r��A��јB����R�������`dy�1����k`���8Vz ��>s��u"I�hCd�Ќ4]�3Y������`����[&V���ȹԮ��A�i+0I;��w���z�$�V��f�ڄҚ<��N3恳mmY�9�ߤ����)'�Ii&��L�RX��}���|>�=��ɻF;8�(��,����%AAl�)���2DB���e3�"7�����M��@�L�S mt������j�f$3�{��.��%��Z�!�q*ɚDΞ�D�f��;�ؒ�K U�gj����b����!87�p9�QȾ��&s0Ƴ�a 	�H8d�r�R�q<���GL�X�u�%̐�
����L>�.�1����>� 0�vԃ�J%Gj�dX�'����I�r��F%�A7�@��L��n�򯓺$����6��G0a�M���5M�'R;���66
V�qg�;��A̮I��o�S���1�ja����Pr�+�SF#
�ZG�4)����� ���P1`�fi�}i���=��Ј�m+�۵N������ê^%�D��e*䛑6j�i`D�
�V�a�xNE;���D8+C�`�@"d�39�L���閿H�Rx6I0I%�Y��;*/sa��``�4���F,r��6��X0��k�H908&������z�a!e�ݏ�%'�*�9g���e(��V�Xc\�XE�!#ٌ�( 9G����p��L:MCɹӗ2���,=� Wg��ģ��](�A����"�|�J�����V��DGk��@�(����ɤ��|yD�3����82ِR���ղH֥h��ԥ���H6e�D���1$����Hz�@�,��-,j���R��3�(MEf�+&��:��킳�y��B��a�����]3�!|"ei`��G��g����|���2ӵ�^w�֮e>ɹeo�7=9��'��� �Հ{��Y����tރ���$�����ۍLlFbD�pǋ���^E}�Hz�\�vl�L<�5<���Yk��g�P��Z���ϕa��B\碜m��4�٘s�5��@���i��9+�>c}3>{m���Fd9�t��V��u���,o!��*���(<�V�}����6�k[&�����79�Y���R��a(�^(u��\�����3�gE���/����.�2��:MH����驘��P�0���0����j͋�J���KW��\�~��폯��9q�u'�J�SE�g�C(����w�8c$�w��RU�8*�	���,�eт}LS�Ӈ�DV�`�NH��=,բ������������?��UO�ڴ��]�pg�N���)
����>J~3���;�<�?��gXrb$8��B��͊e5+O�r�|C�mM�x�T`O�'�G�t^�G�:�PlRF����V�d��^����Q�N���s}�7��(\���R2A��9�S*��@�@�G<�_�(-Mn�7�|�"�C���nrY(�Ğ��tGf�f�X��qf�|���7Tm:��:�4��`K���8ـm�&<kR���a�PY8O>h�h<�ͥ�{��-�
;�(l/H�r8qQ��Ԯ�ޔZf�O�M�q��J�LApYW�l@��\&9.�aЄ�%�l\wŪ�E0�͘#+J^��Le񻆅Q�f��/ %��Q^P�1��{y��bN���x$�S ��3z�>a��(�q��FRS��X����:���8�Յ����//�a�dG�0� 4�ڮ�V�s>�ٵF��"w��Z�?;��4�,*t
g~e%��4�U[v��@�54,M;)a��/Q���bdW���ס��DK�cP�J�*H3��7XSl_#L����ϫ#�č�'id,F�[�����D�d�U�|q��a���cT��zR�r���VO4�DPX�n�s�/���5��߼�/ibW�M���1>Y"m�0�����=��[�q�1j]�E���F�U�m�\���
W���w��,+ǔb�Ŧ��?+JiW�_��jA���7�ʶ�EQ@l|��p�砪�h���q�����P��/��oi�4_>P���W��[��������Lߍ�ȱ�J`�)������+���*���_�����%Í۾�4�C�3�9��(�;�m���)/� ���ݿ����p�p�3�Yc��5=�+�6�)^L����5 bO�������Ѕ4��qk#,���`țl��~�� zx�i_���r��m~�"�܉xdk*ݬ�b�o����cW�j-Gm�kdB���L j�0�̳���c|)`�7�A����`����P�[X3Jr��_E֮��vef��߻^�����%�ܞ7S@`%�b�����ڪfD���$9��͈]�5�W�"�L)w���>`���vR<�=BS2�rћq�'0`&~� Ϲ�8;b���f$����D~���icu�Ƭ��)�j%���>>��$��+�ES{-~�uş�k��_O��M`����(�߻3'��ދ]�E�*�	�'�X�~�.���ߴ1y��Z���YXY���W~�W��������hr�/2'_Soz�;�����bo�b���?mMc�TӃ�%�����R��q��B�'�a�9Ƃ����cѷ_��~)z����o� ��_[b�/x2�FA�����W�5�BjEc����'�����k����6,���oK�������S�֩S��/I�      o      x������ � �      p   4   x�3���q�wt����2�p�B�\���!��G77.'4(���� �(;      r   t   x�3�4202�54�52Q04�22�2��3123���'e�%��y�
!�y���yP��_zF��c'�SQJObCKKc��������\L9NKcsCN#�? ����� z�)�      t   �  x���Mkg��;��9�T�����jjG4ؘrٮ�vk�J�V�>�9��Ck�B)�1�8���%J��{�7���	Ҵ0"Kk�7�}�?orVòMs�r���0����c��m}�W&��fR��Ī�TÓL�xv���a�6<#(e��H����j�{�:�跜�!��V5z���jt�:�A�.���E�A�9/tQ��'j���mR����'z���b�PY��G햺_hk�U'�F�lȢ����{���!���Kj#oE;�]�7B�Bw��_���\� T�u)��]���fy^0ңП��w��I��O�L4wY���Q��p~��I���2�|�ph�q �J;�ؐ̆L.ˑZ/ߩf5zmh �T�R���z�+�=�4���c�<UP��ɟ?V����q�6�=mł[���)"��U��w��x���\��d
?r��f5|Y��-t{7��:>b��;ثFr��}�4^�e9*=�M9��6Ɨ2e�b��f�<@>m�����%S�2֒�0����LQ;���_3���O�,K���������b����D�֯����%K+�a���a54��@=58?�-;��Aװ�%[v�ul���<�l�i�<Ԯ��|�e'=�o"�b,K��e^��e�e	hH������Q
�� GVt�Su��$G�6돻]^ ���1>���]�myb؆c�#ˤ�P����g�lrd����;ugp���92=�$�3�/�c�שj�~n]pə����Kʷ�սΐ�l���n.Ƿ�#g�����O��O�L�_A�]��ih@�8!��O��\aV��î�n�\�ty4[ �je�I�<�'W�)�,rej]�F��%&�T}?�Y	rEۦ@t+u�V&�g)��0�0I���T���q9"yb�� � �z�A�֐�ݰnv�:�{M��=���T�E7�L��D�^Iʓ�L���#��|��\��&��F�r���.���6H�%�q�J2�1l��/��`��1��=��'Sou��i�ŏ�[9Ό������*������׭(��C���V˃ti���q�_�$�1l#�����,D�6�dQ���3<��4��r��,��˅9�xt>�+2eb����|j��f]L�2�A��ײȒ	oo���w�M�Lhȷ/r$��=�9d�Eh�i���.Y�sx�-m��Ȓ��=t�gD��ɚ�0��X=��n26 K�:���.u�E��^�P���DH�L����Ľ�n��=�1�7�.�z�r.����7��ގA�]��:�Ѐ�ɞ�&L��C����-:V�db�@��ִ�0hQ_�
�@�֍�K�L��0�X�6�
�!�%�\�x�����p}re�]��Eo	Z@�L�k	��ebH�L�7�;���� oem�c(0#2�$O��[�����?��k�'�hM�u6�&O����0�!O�����GR/�Q���[ߌ״��ȓId-�T�[� �3}��_����[d�����2�!�2���yR�ʀ�����
�)6SM�e�=H�2�L�U��ɗɥ�ui��`�C��v������p�%_�1��O�� &�y|�s���~�1�'_&��[�S0��2%M�����	����t���� �G��"[��ڔi3m��2�5uC��1Ϥ@&��[�G�j���٢@&�Z� �H'�k�Ԓ�Z
d���t��Wã|�h���B�R S�V5<-Tqu��x���w�������ϓ      v      x������ � �      x      x�3�4�2�4�2c#�=... ��      y   �   x����
�0���Sx�qw��ēFT�VP����`+��֧�AR� �s�]�����>��n��$(e�tr���o/�sEV]2W���zi�G�=���0Z�@!H��<9��A{���9��A�Z�iYu4^�c��%��O��Iz������C������z785Tl(�|CT e���c|D3��9���w#     