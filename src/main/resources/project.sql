PGDMP          
                 z            movies    14.5    15.0 i    }           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
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
                   postgres    false    5            �            1259    20328    category    TABLE        CREATE TABLE public.category (
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
       public         heap    postgres    false    5            �            1259    20333    category_id_seq    SEQUENCE     x   CREATE SEQUENCE public.category_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public.category_id_seq;
       public          postgres    false    5    210            �           0    0    category_id_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE public.category_id_seq OWNED BY public.category.id;
          public          postgres    false    211            �            1259    20334    comment    TABLE     �   CREATE TABLE public.comment (
    id bigint NOT NULL,
    created_at timestamp without time zone,
    updated_at timestamp without time zone,
    comment character varying(255),
    product_id bigint,
    user_id bigint
);
    DROP TABLE public.comment;
       public         heap    postgres    false    5            �            1259    20337    comment_id_seq    SEQUENCE     w   CREATE SEQUENCE public.comment_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE public.comment_id_seq;
       public          postgres    false    212    5            �           0    0    comment_id_seq    SEQUENCE OWNED BY     A   ALTER SEQUENCE public.comment_id_seq OWNED BY public.comment.id;
          public          postgres    false    213            �            1259    20338    country    TABLE     �   CREATE TABLE public.country (
    id bigint NOT NULL,
    created_at timestamp without time zone,
    updated_at timestamp without time zone,
    created_by bigint,
    updated_by bigint,
    enable boolean NOT NULL,
    name character varying(255)
);
    DROP TABLE public.country;
       public         heap    postgres    false    5            �            1259    20341    country_id_seq    SEQUENCE     w   CREATE SEQUENCE public.country_id_seq
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
       public          postgres    false    5            �            1259    20342    image    TABLE     S  CREATE TABLE public.image (
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
       public         heap    postgres    false    5            �            1259    20347    image_id_seq    SEQUENCE     u   CREATE SEQUENCE public.image_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.image_id_seq;
       public          postgres    false    216    5            �           0    0    image_id_seq    SEQUENCE OWNED BY     =   ALTER SEQUENCE public.image_id_seq OWNED BY public.image.id;
          public          postgres    false    217            �            1259    20348 
   prefecture    TABLE     �   CREATE TABLE public.prefecture (
    id bigint NOT NULL,
    created_at timestamp without time zone,
    updated_at timestamp without time zone,
    name character varying(255)
);
    DROP TABLE public.prefecture;
       public         heap    postgres    false    5            �            1259    20351    prefecture_id_seq    SEQUENCE     z   CREATE SEQUENCE public.prefecture_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 (   DROP SEQUENCE public.prefecture_id_seq;
       public          postgres    false    218    5            �           0    0    prefecture_id_seq    SEQUENCE OWNED BY     G   ALTER SEQUENCE public.prefecture_id_seq OWNED BY public.prefecture.id;
          public          postgres    false    219            �            1259    20357    product_id_seq    SEQUENCE     w   CREATE SEQUENCE public.product_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE public.product_id_seq;
       public          postgres    false    5            �            1259    20352    product    TABLE     �  CREATE TABLE public.product (
    id bigint DEFAULT nextval('public.product_id_seq'::regclass) NOT NULL,
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
    mode bigint,
    until timestamp without time zone,
    price bigint,
    sale_price bigint,
    sale boolean,
    slug character varying(255)
);
    DROP TABLE public.product;
       public         heap    postgres    false    221    5            �            1259    20358    product_tags    TABLE     c   CREATE TABLE public.product_tags (
    products_id bigint NOT NULL,
    tags_id bigint NOT NULL
);
     DROP TABLE public.product_tags;
       public         heap    postgres    false    5            �            1259    20361    roles    TABLE     V   CREATE TABLE public.roles (
    id bigint NOT NULL,
    name character varying(60)
);
    DROP TABLE public.roles;
       public         heap    postgres    false    5            �            1259    20364    roles_id_seq    SEQUENCE     u   CREATE SEQUENCE public.roles_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.roles_id_seq;
       public          postgres    false    5    223            �           0    0    roles_id_seq    SEQUENCE OWNED BY     =   ALTER SEQUENCE public.roles_id_seq OWNED BY public.roles.id;
          public          postgres    false    224            �            1259    20365    shop    TABLE     �  CREATE TABLE public.shop (
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
       public         heap    postgres    false    5            �            1259    20370    shop_id_seq    SEQUENCE     t   CREATE SEQUENCE public.shop_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 "   DROP SEQUENCE public.shop_id_seq;
       public          postgres    false    225    5            �           0    0    shop_id_seq    SEQUENCE OWNED BY     ;   ALTER SEQUENCE public.shop_id_seq OWNED BY public.shop.id;
          public          postgres    false    226            �            1259    20371    sub_category    TABLE     C  CREATE TABLE public.sub_category (
    id bigint NOT NULL,
    created_at timestamp without time zone,
    updated_at timestamp without time zone,
    created_by bigint,
    updated_by bigint,
    enable boolean NOT NULL,
    name character varying(255),
    category_id bigint NOT NULL,
    slug character varying(255)
);
     DROP TABLE public.sub_category;
       public         heap    postgres    false    5            �            1259    20376    sub_category_id_seq    SEQUENCE     |   CREATE SEQUENCE public.sub_category_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE public.sub_category_id_seq;
       public          postgres    false    227    5            �           0    0    sub_category_id_seq    SEQUENCE OWNED BY     K   ALTER SEQUENCE public.sub_category_id_seq OWNED BY public.sub_category.id;
          public          postgres    false    228            �            1259    20377    tag    TABLE     �   CREATE TABLE public.tag (
    id bigint NOT NULL,
    created_at timestamp without time zone,
    updated_at timestamp without time zone,
    created_by bigint,
    updated_by bigint,
    enable boolean NOT NULL,
    name character varying(255)
);
    DROP TABLE public.tag;
       public         heap    postgres    false    5            �            1259    20380 
   tag_id_seq    SEQUENCE     s   CREATE SEQUENCE public.tag_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 !   DROP SEQUENCE public.tag_id_seq;
       public          postgres    false    229    5            �           0    0 
   tag_id_seq    SEQUENCE OWNED BY     9   ALTER SEQUENCE public.tag_id_seq OWNED BY public.tag.id;
          public          postgres    false    230            �            1259    20381 
   user_roles    TABLE     ]   CREATE TABLE public.user_roles (
    user_id bigint NOT NULL,
    role_id bigint NOT NULL
);
    DROP TABLE public.user_roles;
       public         heap    postgres    false    5            �            1259    20384    users    TABLE       CREATE TABLE public.users (
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
       public         heap    postgres    false    5            �            1259    20389    users_id_seq    SEQUENCE     u   CREATE SEQUENCE public.users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.users_id_seq;
       public          postgres    false    232    5            �           0    0    users_id_seq    SEQUENCE OWNED BY     =   ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id;
          public          postgres    false    233            �           2604    20390    category id    DEFAULT     j   ALTER TABLE ONLY public.category ALTER COLUMN id SET DEFAULT nextval('public.category_id_seq'::regclass);
 :   ALTER TABLE public.category ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    211    210            �           2604    20391 
   comment id    DEFAULT     h   ALTER TABLE ONLY public.comment ALTER COLUMN id SET DEFAULT nextval('public.comment_id_seq'::regclass);
 9   ALTER TABLE public.comment ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    213    212            �           2604    20392 
   country id    DEFAULT     h   ALTER TABLE ONLY public.country ALTER COLUMN id SET DEFAULT nextval('public.country_id_seq'::regclass);
 9   ALTER TABLE public.country ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    215    214            �           2604    20393    image id    DEFAULT     d   ALTER TABLE ONLY public.image ALTER COLUMN id SET DEFAULT nextval('public.image_id_seq'::regclass);
 7   ALTER TABLE public.image ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    217    216            �           2604    20394    prefecture id    DEFAULT     n   ALTER TABLE ONLY public.prefecture ALTER COLUMN id SET DEFAULT nextval('public.prefecture_id_seq'::regclass);
 <   ALTER TABLE public.prefecture ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    219    218            �           2604    20396    roles id    DEFAULT     d   ALTER TABLE ONLY public.roles ALTER COLUMN id SET DEFAULT nextval('public.roles_id_seq'::regclass);
 7   ALTER TABLE public.roles ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    224    223            �           2604    20397    shop id    DEFAULT     b   ALTER TABLE ONLY public.shop ALTER COLUMN id SET DEFAULT nextval('public.shop_id_seq'::regclass);
 6   ALTER TABLE public.shop ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    226    225            �           2604    20398    sub_category id    DEFAULT     r   ALTER TABLE ONLY public.sub_category ALTER COLUMN id SET DEFAULT nextval('public.sub_category_id_seq'::regclass);
 >   ALTER TABLE public.sub_category ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    228    227            �           2604    20399    tag id    DEFAULT     `   ALTER TABLE ONLY public.tag ALTER COLUMN id SET DEFAULT nextval('public.tag_id_seq'::regclass);
 5   ALTER TABLE public.tag ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    230    229            �           2604    20400    users id    DEFAULT     d   ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq'::regclass);
 7   ALTER TABLE public.users ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    233    232            c          0    20328    category 
   TABLE DATA           l   COPY public.category (id, created_at, updated_at, created_by, updated_by, enable, name, search) FROM stdin;
    public          postgres    false    210   G       e          0    20334    comment 
   TABLE DATA           [   COPY public.comment (id, created_at, updated_at, comment, product_id, user_id) FROM stdin;
    public          postgres    false    212   h�       g          0    20338    country 
   TABLE DATA           c   COPY public.country (id, created_at, updated_at, created_by, updated_by, enable, name) FROM stdin;
    public          postgres    false    214   ۀ       i          0    20342    image 
   TABLE DATA           �   COPY public.image (id, created_at, updated_at, created_by, updated_by, priority, url_medium, url_small, product_id, shop_id) FROM stdin;
    public          postgres    false    216   e�       k          0    20348 
   prefecture 
   TABLE DATA           F   COPY public.prefecture (id, created_at, updated_at, name) FROM stdin;
    public          postgres    false    218   ��       m          0    20352    product 
   TABLE DATA           �   COPY public.product (id, created_at, updated_at, created_by, updated_by, description, description_sort, enable, hot, name, rating, tiktok, country_id, shop_id, sub_category_id, mode, until, price, sale_price, sale, slug) FROM stdin;
    public          postgres    false    220   7�       o          0    20358    product_tags 
   TABLE DATA           <   COPY public.product_tags (products_id, tags_id) FROM stdin;
    public          postgres    false    222   �       p          0    20361    roles 
   TABLE DATA           )   COPY public.roles (id, name) FROM stdin;
    public          postgres    false    223   $�       r          0    20365    shop 
   TABLE DATA           �   COPY public.shop (id, created_at, updated_at, created_by, updated_by, active, address, email, name, number, user_id, telegram_group, telegram_id) FROM stdin;
    public          postgres    false    225   h�       t          0    20371    sub_category 
   TABLE DATA           {   COPY public.sub_category (id, created_at, updated_at, created_by, updated_by, enable, name, category_id, slug) FROM stdin;
    public          postgres    false    227   ��       v          0    20377    tag 
   TABLE DATA           _   COPY public.tag (id, created_at, updated_at, created_by, updated_by, enable, name) FROM stdin;
    public          postgres    false    229   Ҳ       x          0    20381 
   user_roles 
   TABLE DATA           6   COPY public.user_roles (user_id, role_id) FROM stdin;
    public          postgres    false    231   �       y          0    20384    users 
   TABLE DATA              COPY public.users (id, created_at, updated_at, address, avatar, birthday, email, is_account_non_expired, is_account_non_locked, is_credentials_non_expired, is_enabled, mobile, password, prefecture_id, create_expiry, create_token, reset_expiry, reset_token, username, shop_id) FROM stdin;
    public          postgres    false    232   �       �           0    0    category_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.category_id_seq', 53, true);
          public          postgres    false    211            �           0    0    comment_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.comment_id_seq', 1, false);
          public          postgres    false    213            �           0    0    country_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.country_id_seq', 99, true);
          public          postgres    false    215            �           0    0    hibernate_sequence    SEQUENCE SET     C   SELECT pg_catalog.setval('public.hibernate_sequence', 1197, true);
          public          postgres    false    209            �           0    0    image_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.image_id_seq', 34, true);
          public          postgres    false    217            �           0    0    prefecture_id_seq    SEQUENCE SET     @   SELECT pg_catalog.setval('public.prefecture_id_seq', 63, true);
          public          postgres    false    219            �           0    0    product_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.product_id_seq', 10, true);
          public          postgres    false    221            �           0    0    roles_id_seq    SEQUENCE SET     :   SELECT pg_catalog.setval('public.roles_id_seq', 4, true);
          public          postgres    false    224            �           0    0    shop_id_seq    SEQUENCE SET     9   SELECT pg_catalog.setval('public.shop_id_seq', 1, true);
          public          postgres    false    226            �           0    0    sub_category_id_seq    SEQUENCE SET     D   SELECT pg_catalog.setval('public.sub_category_id_seq', 1421, true);
          public          postgres    false    228            �           0    0 
   tag_id_seq    SEQUENCE SET     9   SELECT pg_catalog.setval('public.tag_id_seq', 1, false);
          public          postgres    false    230            �           0    0    users_id_seq    SEQUENCE SET     :   SELECT pg_catalog.setval('public.users_id_seq', 6, true);
          public          postgres    false    233            �           2606    20403 !   users UK6j5t70rd2eub907qysjvvd76n 
   CONSTRAINT     _   ALTER TABLE ONLY public.users
    ADD CONSTRAINT "UK6j5t70rd2eub907qysjvvd76n" UNIQUE (email);
 M   ALTER TABLE ONLY public.users DROP CONSTRAINT "UK6j5t70rd2eub907qysjvvd76n";
       public            postgres    false    232            �           2606    20405    category category_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.category
    ADD CONSTRAINT category_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.category DROP CONSTRAINT category_pkey;
       public            postgres    false    210            �           2606    20407    comment comment_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.comment
    ADD CONSTRAINT comment_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.comment DROP CONSTRAINT comment_pkey;
       public            postgres    false    212            �           2606    20409    country country_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.country
    ADD CONSTRAINT country_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.country DROP CONSTRAINT country_pkey;
       public            postgres    false    214            �           2606    20411    image image_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.image
    ADD CONSTRAINT image_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.image DROP CONSTRAINT image_pkey;
       public            postgres    false    216            �           2606    20413    prefecture prefecture_pkey 
   CONSTRAINT     X   ALTER TABLE ONLY public.prefecture
    ADD CONSTRAINT prefecture_pkey PRIMARY KEY (id);
 D   ALTER TABLE ONLY public.prefecture DROP CONSTRAINT prefecture_pkey;
       public            postgres    false    218            �           2606    20415    product product_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.product
    ADD CONSTRAINT product_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.product DROP CONSTRAINT product_pkey;
       public            postgres    false    220            �           2606    20417    roles roles_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.roles
    ADD CONSTRAINT roles_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.roles DROP CONSTRAINT roles_pkey;
       public            postgres    false    223            �           2606    20419    shop shop_pkey 
   CONSTRAINT     L   ALTER TABLE ONLY public.shop
    ADD CONSTRAINT shop_pkey PRIMARY KEY (id);
 8   ALTER TABLE ONLY public.shop DROP CONSTRAINT shop_pkey;
       public            postgres    false    225            �           2606    20421    sub_category sub_category_pkey 
   CONSTRAINT     \   ALTER TABLE ONLY public.sub_category
    ADD CONSTRAINT sub_category_pkey PRIMARY KEY (id);
 H   ALTER TABLE ONLY public.sub_category DROP CONSTRAINT sub_category_pkey;
       public            postgres    false    227            �           2606    20423    tag tag_pkey 
   CONSTRAINT     J   ALTER TABLE ONLY public.tag
    ADD CONSTRAINT tag_pkey PRIMARY KEY (id);
 6   ALTER TABLE ONLY public.tag DROP CONSTRAINT tag_pkey;
       public            postgres    false    229            �           2606    20425 "   roles uk_nb4h0p6txrmfc0xbrd1kglp9t 
   CONSTRAINT     ]   ALTER TABLE ONLY public.roles
    ADD CONSTRAINT uk_nb4h0p6txrmfc0xbrd1kglp9t UNIQUE (name);
 L   ALTER TABLE ONLY public.roles DROP CONSTRAINT uk_nb4h0p6txrmfc0xbrd1kglp9t;
       public            postgres    false    223            �           2606    20427    user_roles user_roles_pkey 
   CONSTRAINT     f   ALTER TABLE ONLY public.user_roles
    ADD CONSTRAINT user_roles_pkey PRIMARY KEY (user_id, role_id);
 D   ALTER TABLE ONLY public.user_roles DROP CONSTRAINT user_roles_pkey;
       public            postgres    false    231    231            �           2606    20429    users users_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.users DROP CONSTRAINT users_pkey;
       public            postgres    false    232            �           2606    20430     shop FK36nknc2cfkui55nrprhr55d5v    FK CONSTRAINT     �   ALTER TABLE ONLY public.shop
    ADD CONSTRAINT "FK36nknc2cfkui55nrprhr55d5v" FOREIGN KEY (user_id) REFERENCES public.users(id);
 L   ALTER TABLE ONLY public.shop DROP CONSTRAINT "FK36nknc2cfkui55nrprhr55d5v";
       public          postgres    false    225    232    3527            �           2606    20435 (   product_tags FK4n67l5ht7b0whs4sragr8axph    FK CONSTRAINT     �   ALTER TABLE ONLY public.product_tags
    ADD CONSTRAINT "FK4n67l5ht7b0whs4sragr8axph" FOREIGN KEY (products_id) REFERENCES public.product(id);
 T   ALTER TABLE ONLY public.product_tags DROP CONSTRAINT "FK4n67l5ht7b0whs4sragr8axph";
       public          postgres    false    3511    220    222            �           2606    20440 !   users FK4qu2c5f8i7hc6vf050wb0k948    FK CONSTRAINT     �   ALTER TABLE ONLY public.users
    ADD CONSTRAINT "FK4qu2c5f8i7hc6vf050wb0k948" FOREIGN KEY (shop_id) REFERENCES public.shop(id);
 M   ALTER TABLE ONLY public.users DROP CONSTRAINT "FK4qu2c5f8i7hc6vf050wb0k948";
       public          postgres    false    225    3517    232            �           2606    20445 #   product FK6cighpvj59jngocmr7ipusavu    FK CONSTRAINT     �   ALTER TABLE ONLY public.product
    ADD CONSTRAINT "FK6cighpvj59jngocmr7ipusavu" FOREIGN KEY (sub_category_id) REFERENCES public.sub_category(id);
 O   ALTER TABLE ONLY public.product DROP CONSTRAINT "FK6cighpvj59jngocmr7ipusavu";
       public          postgres    false    220    3519    227            �           2606    20450 &   user_roles FK7ov27fyo7ebsvada1ej7qkphl    FK CONSTRAINT     �   ALTER TABLE ONLY public.user_roles
    ADD CONSTRAINT "FK7ov27fyo7ebsvada1ej7qkphl" FOREIGN KEY (user_id) REFERENCES public.users(id);
 R   ALTER TABLE ONLY public.user_roles DROP CONSTRAINT "FK7ov27fyo7ebsvada1ej7qkphl";
       public          postgres    false    231    3527    232            �           2606    20455 #   product FKaqw8hq5gyp5v7513xkvdac0lh    FK CONSTRAINT     �   ALTER TABLE ONLY public.product
    ADD CONSTRAINT "FKaqw8hq5gyp5v7513xkvdac0lh" FOREIGN KEY (country_id) REFERENCES public.country(id);
 O   ALTER TABLE ONLY public.product DROP CONSTRAINT "FKaqw8hq5gyp5v7513xkvdac0lh";
       public          postgres    false    220    3505    214            �           2606    20460 !   image FKd0g1tmjbdkqd13kkeo6d3tcep    FK CONSTRAINT     �   ALTER TABLE ONLY public.image
    ADD CONSTRAINT "FKd0g1tmjbdkqd13kkeo6d3tcep" FOREIGN KEY (shop_id) REFERENCES public.shop(id);
 M   ALTER TABLE ONLY public.image DROP CONSTRAINT "FKd0g1tmjbdkqd13kkeo6d3tcep";
       public          postgres    false    216    3517    225            �           2606    20465 &   user_roles FKej3jidxlte0r8flpavhiso3g6    FK CONSTRAINT     �   ALTER TABLE ONLY public.user_roles
    ADD CONSTRAINT "FKej3jidxlte0r8flpavhiso3g6" FOREIGN KEY (role_id) REFERENCES public.roles(id);
 R   ALTER TABLE ONLY public.user_roles DROP CONSTRAINT "FKej3jidxlte0r8flpavhiso3g6";
       public          postgres    false    231    3513    223            �           2606    20470 #   product FKett17tt7p4pbuitp0if89kdt5    FK CONSTRAINT     �   ALTER TABLE ONLY public.product
    ADD CONSTRAINT "FKett17tt7p4pbuitp0if89kdt5" FOREIGN KEY (shop_id) REFERENCES public.shop(id);
 O   ALTER TABLE ONLY public.product DROP CONSTRAINT "FKett17tt7p4pbuitp0if89kdt5";
       public          postgres    false    220    3517    225            �           2606    20475 !   image FKf34pcxeeyw3it5v2rthteef7v    FK CONSTRAINT     �   ALTER TABLE ONLY public.image
    ADD CONSTRAINT "FKf34pcxeeyw3it5v2rthteef7v" FOREIGN KEY (product_id) REFERENCES public.product(id);
 M   ALTER TABLE ONLY public.image DROP CONSTRAINT "FKf34pcxeeyw3it5v2rthteef7v";
       public          postgres    false    216    3511    220            �           2606    20480 !   users FKhf381xrw2kuxkx75jyl8pewp4    FK CONSTRAINT     �   ALTER TABLE ONLY public.users
    ADD CONSTRAINT "FKhf381xrw2kuxkx75jyl8pewp4" FOREIGN KEY (prefecture_id) REFERENCES public.prefecture(id);
 M   ALTER TABLE ONLY public.users DROP CONSTRAINT "FKhf381xrw2kuxkx75jyl8pewp4";
       public          postgres    false    232    3509    218            �           2606    20485 #   comment FKkwd7w0meugs0nu95w48hqhw2w    FK CONSTRAINT     �   ALTER TABLE ONLY public.comment
    ADD CONSTRAINT "FKkwd7w0meugs0nu95w48hqhw2w" FOREIGN KEY (user_id) REFERENCES public.users(id);
 O   ALTER TABLE ONLY public.comment DROP CONSTRAINT "FKkwd7w0meugs0nu95w48hqhw2w";
       public          postgres    false    212    3527    232            �           2606    20490 #   comment FKlo59icyee0u6jucfb68x22o3v    FK CONSTRAINT     �   ALTER TABLE ONLY public.comment
    ADD CONSTRAINT "FKlo59icyee0u6jucfb68x22o3v" FOREIGN KEY (product_id) REFERENCES public.product(id);
 O   ALTER TABLE ONLY public.comment DROP CONSTRAINT "FKlo59icyee0u6jucfb68x22o3v";
       public          postgres    false    3511    212    220            �           2606    20495 (   product_tags FKmb0hq4cm6sa2ji8a1ymqfyax4    FK CONSTRAINT     �   ALTER TABLE ONLY public.product_tags
    ADD CONSTRAINT "FKmb0hq4cm6sa2ji8a1ymqfyax4" FOREIGN KEY (tags_id) REFERENCES public.tag(id);
 T   ALTER TABLE ONLY public.product_tags DROP CONSTRAINT "FKmb0hq4cm6sa2ji8a1ymqfyax4";
       public          postgres    false    3521    222    229            �           2606    20500 (   sub_category FKshci6pjlkmq0u7m9m1arwqbpb    FK CONSTRAINT     �   ALTER TABLE ONLY public.sub_category
    ADD CONSTRAINT "FKshci6pjlkmq0u7m9m1arwqbpb" FOREIGN KEY (category_id) REFERENCES public.category(id);
 T   ALTER TABLE ONLY public.sub_category DROP CONSTRAINT "FKshci6pjlkmq0u7m9m1arwqbpb";
       public          postgres    false    227    210    3501            c     x�3�4202�54�52Q04�2��21�314766�'e�%�!w��T)J�KW�{�{=gx~nj��sN~IFj�nrb	�1E6�>ܵSAM���JN���b]�̔b]�ĤL����p��d�#��p:�&��T�M5����+��3����鑚�S�6՜"S�3/�Tp9�� 9��3�!aB����\����pw{�B���8�xl�[bqFf~��crrjqq~,,(KA�we*�^��'��t�ц���M���͌-��%B
[�L���EK�1z\\\ ��      e   c   x�3�4202�54�52Q04�2��26�3273�0�'���P����P�_�����99��(50%1/]!=3Q!%�(�L��k�eL����p���qqq ��4�      g   z   x�3�4202�54�5�T04�2��22�3�0645�'e�%�
a�w��(�%�rQf�_��]kK��Z�ǅWa�</�S,}�{b2�	EF��p��d.S�q���2��߇�wr��qqq [�^t      i     x����r�0�k}
_�4B�O]PWO�3����x��O��u�d��/3�o�� T ���(CPVU�#�D,4��{|���������������5�_9 �O���R�g3��W-����X���� /#�
��?����Z����hos���n�����Ƅ�qU�%r��E��6��`-]�c�yE���r�R f�9�dL}��a�7ڸ2���p����H>_�f@+���/��ݴ�
`D�N 8W��H��Z��~�92��8$a(�ݵ�2�
����LDr�Q5<�j��>��(	���vV��+c�P�P�eF�{�v&��0��L�t/)�aH�]<3��6����t���ƣ��
`@���-�ec�s��N�m&[O �$K��"�br��w̰��r�>��I\M�|�A�kY�b�*���Ǆ�"9k�zX���mڵ{��E$݌3�+����\o�F�c�p./�V�I�?X�]��J��QyV"g�끻�������˪�~J��QT\�a�%�[$g�-X�~��1�4H�}�2�YLҭA(Er֋ѢЛ�Ǭpfժn��g����2�
F���4�Y�.{c�c��^���	%g�E0���˙��+�a��t=���sVN�WO� �~@6 �k��G"��~7�l�yX�>��j5��1��)T��(6h��r�v�`u=��ƈ��8:.��O$#9�ʮ��M�ۘ���)�b��y��	�ّ\vX���J��z/��M��R,�'�dx      k   �  x�}�MS�8�ϫ_�۞ƥO[���LA�e�j���$"va,�P��ӑ�sy1קӖ����ɥ�"��V"�*�WUd&�"�K�{lw��z�c�.��j	�;����*�V�K��w�j���<�вN���w�P?��*�3�-s�U���I.^��.�Om���%��k��8�%ßs�,q�&��$�ulC�WM�Y���'���m���}xل�9`j3^P ���﫻��Wa<���ZX��Ԍ���m��q�������K��sߑ��'�
$`�i.��7��Y�ZR�ҁi!��:�C7��@�چmI��0c�THW3��\���m��@R�T9�Ҳ�nbh�(HL�Y�������Ǧ	�&�q�S�qB����1����mW���.��]�6잆�P\&��EkF�a3�N"��L�\��]W��*��2<��(U��o��B�(.��)X�3������-�t�ŌR��g���I�Y��R�Pl3�?^du߇w��t+�
�ֲ�g�PjW߆���HW.��)جftlVg$ޞ"�dK���O�8���%)f�$�sT���#I���n7:�՜�+79��	�8��7MjO
(�LE�4���l�K��S@�d')�|�:^�c�*R��*�j�9�ۡ�^�U��Hv:WV���P��]�l�֧�*�]27&/�.�P�&�0��X��JEt�h�Y��<��p�Y|�	��=����m�,�����gr0���Fmd	�3���39A��V�&uB)4��f�$��|mV;t�]������*��HE&]A;�:����}�3��+2�
��:�����0rqM�#Gٟ��j�Q&����~B7U[���o�"�� ���@c�Rrn�aꚡ%�L�`B�f{^�U�4Ρu�	��Lh��my��m�I��!��Lma5�htv_��
L�d�LJm`Üѣ�5��H�2S4�,v?�_���.4]Y���݂�5w����:���>��N:�Bi�/��3��]�9n�3#{M�:R��
z���5�K
H[mc�!��%i�茾�m�ڛA�%3���'FtC35!�� ���Gc$�x���3H���Wj��fj���ABRiu��bF�8T��xv�H�*��lB����ߪI��Ti����h�k�w1���j��ѵ��x�al�r�2SP�����X4�o����1�k{�      m      x��[[oǕ~�~E	� �23��"���J�n�D9&mo 	rMw�t��]㾐�]/`������1lZ�z� ��"�����i��K�;���23�dX`#G���N��w.��궻�f���.���J����b��t|~y�q�:���X�0�^r5~�9q}���E�f�L6]��3n���!�ly������ʑ+ko��0p���l����9��[���܍�����p����<굆���ra�\/Uy�x�z�(7w�t.N���D�)��\�'A��/ME⅞L=~-��W��4﷽^����ig���U乁$���"H�N�r��ŲznCD��^3�;j��c��L!�x|e`i�j|2�L~���m/�\q~����R]����=���+߫�9�~F+�=�)�@?O���/���^������s֓Yeh���J2���@���2s|d"��2G ���U�AV!oq5�{�8�xo�#X	k԰�v�t�Ǚ�� ���R%���-��HE?Q�8d��^�h�sJ��jliF/��oӼDVY��/�� B�a���s�*'��8�m�BƆ�J4^^=KJ+��N���=��Ȯ"�0/ټWs���*�d��P��8T+d����SC��iX�y�1�H!��d*��J��8��=/�&�ɱSK�J���+n�s�X}QP$*�-	\:ab1P2$�
)���
����C"BY����$�t��i9�:TiJ>��ד��D����o�P�ղH}��.���^�>��f�6�|(ɣ���!��2Z*�IL����D��Տ`Z+29`� fE� N�`A������cA�*RD���$1� r�F�՝�pcE:"6�x;}���u}6DVD/��gc��XN�u5�1���y���hn������Epr�0����PCp����nx�F2�J6���K�ъ�����vu/�e�@�$`Q��0=���FrZ-�=�k���YB�]�ކ`3�hC�C����U��N��tAQ|� ��n��ll��qJ��C�6�/f�����	|%�(�^�3��)��(��셹˼�TܓΦGF�R��
Z�'��V{k4�N2��oW�9��Pmˑ��טĹ<d�4��dD����{:J3/�e��f�%e���W<k��y���ù獙�� ����ʍ_�^2"ܜ��$�
� �BƲ����U����
ǤoD��IG l���;��[^@X!T$�U�=�Tpe�ؠ��o�-� �V��3�{��L�Pd��s��	��	཭��gX��߂6��o��B���#�O��U��,�����$*��jU�1�_�Yn��]^=s���k/T�L�}�� N��
'O����~���3H?#O��-�%gdh%R���R�`΃�!��&2�9�	�,�[�-�x����Kg�[�����������'��9W?��{[�{����j�ޠ���^�w}�9�.�f��&~��㉝�ى�`j�fƖ�v�%`VU7D�����W��d,�Q8�4Ҿ��Q2��U�P���]��usm�HΒ��g�/��pGf�=���x��\�bU����-�S�Q��^�E����{*O�o��xE�Eq��^&X����bz�K�f�����,��6��S�	�ِ��j�L���a��e��sa������y�A�V�oJ�v$�4���{���b����mF��~`�|�:����K$W�MZ���&Ǉs�|��ڀ� ]Z2I�<B!	%S� (YѢ��a�FdYi����d2�wj�5}�Z�� bm�:�����
���r�%Y_ɨ$"�����:A��A~�ɨ%^��X��'���\��xO���Zrz�=����j&�2�r?M^�+V��ӿ�����[PQ
u��h���2@R��H��z%&X��kL�UHV�8 z
IMY ݖC�BT�q o���ȫ�yE9�0-k�2R:�AJ�H%���V��k�5�<�zuE����A��ԅX[�ۑ�Ho{�8D˘��&v�0�b�`�^�R!hO�E�25�Zݕ�~���Ɯ$]���+|H�2'�d��)�G��u_X���u��5<�Qj�PZ��C�/�5�y+�>"�g4��7(�L���^tzx �'J�Ư����#;naz#�l[�@�l�V���<s�u }tb�r���+�Nw��Ư�
)�
ǋSF�K6�c��&@�u�϶��Ѣz���x�?r�W�殭�����fJ�|�G�Fi?�0-L�S�H����(�;�mʆCjb����<)��DQ��)��g�P=nu+�5��`"�4pĞ�H�qm����B1����e��T,�V��8�����Z��ϛ9��ꞹ�������9��xM�DT�5��Y4 ��ɐBױ+��5�6�����qd����ބEM*��y�6Soܠ~�;x��]I^և����-�f9u�[6g��9+��Y��f3ZYQ����y%-��!w�^7�lWU.*Kz�d�u+g�]��W�.���^��t�Mz@�͊�l�<ѭ�1=��;�P�/�*������K(�W��E\?��̡u�`4��܁U���4j�g#=Y����g�<�j/�_~V���}AE��ў�J��(�"�<<�r�n/T��*��(�x�Q.�.�M�~e���k���R�-.�?���?��}ph��tSɳx��mw��#�:��g����S��)�N�}��7 ǎ���qr���ť���>�)����I���ݕ"��L<����6 |'���H��v>�E���'���b��2w����c����B���܉j;])���?"�|�Mt�w�R�{_�5����S��%^���(��0;9�R�{�������b>:�c���W��J��1�iL�X�����{�D����հ�g1>�i��L8��"K�!Xa���`�p�O�C>�2�[VR4��̅k��d	�c����!�&�p�$q��B�ԍ�U���C�qP�nCC�%ha������{��F4�n���6/�;Ɉ��'E@�����q�b�����Y�ޔ�r���'�/��ԃ��j"�
�Q8g��c䈘�a��cŚlP;�Q�4_vT�T�3J�&I�:�������|��4��O�<���YW< My�����x�_�a�02RuETȀ��}�P(92D݉�2���yٌ]�;��r�]W��_B�L��Rھ���>��e�4"t��Ѓ�8<zZbњ|�z��J]c"X��f�������T�:�Q�0�2f�'p2��(p9�(�y��Z���)���3 s�\C\��A��4�������fwMZW?ͱ���ݦۗ���� ?����רĞ��YX�����;�:&3�ܻ�@�������w� F ���/�b��p	t���k�r��i �]116������
Pc�# SI@Cba�#�{��x �K$ S����ƻ$ٻ�<wb����-���q,�����)H����
���H%�%� �#`���sV�9��e�dEOeZ0~J侈I�����o��6P��l�KbY�I%�IϿw�9y(���K)��^� E6��	y��>M>�R>�	Ĥs=�u�����ϐ�i�]2�D���]�X�d������W~�תז$�3�}����M�T�0>�A鑈*&s�Q�d�ݪ�V&\)�����K�
oރ��E���h:ܪ�ݲJ�,&��B.����8�):�2 ��W��d�;�����B���[���a��m�*؄&>�,�t&���@U�k5��B �>�ȧW�4#b5�"e�^l���5�q<h耬i�4	�����;C>�|/*K�DM�!��c�w�?�OOd��PA
������#�j������%;!�6��뱾��u����yc.VCLГ� ��5�E��\I�.��i.��̛����(����⮟
��V<�*39�Nw�s.�%V��!���,b�n��p�s��lCHc�?�:���p�[C�&_�V}a5v��n�����T^���3���n*jmY#�<ȥ�*jAg"������� �   �@��>���
��  ��~LUKg�q �oR '��B��ݥ���A�N|X@����xw( �Q��#�ϵ�3�D&�s�P�,MQ�[�H���~�="Ε�1xj�5x�#}��>����;!hC>�<��
�d�M!�Ҁ��]ʋȪ���w�n���d����6?��kJ��Yg��������'�ȿ��w��)����$z��7sn�̷(��{e�ʉ�W!<�T!���\���rt�8(����ȓb�MC.��t!�f.k�� ���(A��)!̷�Z��UU��s�^h��#��{�z���xWQE��C��YK����uX��efQB/_#�2b��&�a$P���"';[V��ޙJ�F�l�JI1��B�C�-��)m� Ϋ�����һ�|�i��sL��AL�4՘��hHj�F��mU�5O�y�.X*5�G1�KcJZ� ���12�L�o�$��d|+`�D�n\EL�+�Mw��� �|cx�)��d
�A@�T�i��"p��VL�j~[��E�)�k�5h�n��uL�#R.,}�Ys�jm8���~T�{lһ�u7����nr��I �rį$�)RO����p|��%I�/Qr����R�YH��o�JBI�����e'w��g(��.e8A�$0�O���1`��s�x9��&=����ٓ��o�����V���`�Z�Ņ��>�}��t��?S{q�ؿ�Ľa�[��v���[E�,O�N��"���I�V]�����(�;.��[��Ѳ��K����Ot���vI���U���Ģ�8�1|:2��q�����p�;T;��Zj����{�J=aԧ��߿�>�K(Nbv>��8�"K� �)�EA�����M�8�=���7��A.��	PSY8u�̠w1S��ȡ������f��-w�A��M����,��{4|��ƼE4k>J��&�S�G�e���$���ҡ�A̟��e�p�x��n��N��Uo�j=�����K��@�?��nG<���b�3Y�d�Փ$<�5
���	
֨R=�X���w��P֫ҭ�]��їL������������RS��$�����`��,"�" :%�I,�97�,'��(�A���cA��)�֨��oM��ł��l�:���:�nn���)r��Q��Ps�n�z@�N�ї�bt��c2�-��6��N��M��@+�͔�uP���˼��T�q$�Հ�T fZQV�v�k����M�ؖ�)���8���J�S9��~��gr����[���#���<f��gq"�9i��N��%��j�6�RN�ER<��=� NǕA���8薾X"<�g����|r�J��'(�h�EB�}��e$BRjEl��9]�hv�u�(�k��Nr|�����=�þ��<�26|H�}�%����"������j�K���JWK��.�e��	��g}�R\�l�h��Ĕ�̚���
��Z���pR<��ݔQ����0��t53���hz�8���֕m���O�^������_�ϵ;kO-
�F�H�l�~��L�o�=]摝ues�F�����-q�l�ԥ�
�4�R�����3�c�M=#��e
�{��}ό�4�<?������'?�:�T;����Z���� J�n�`�s������e&o q�&�t�Yi�<�*��ޅrk8�w�J_���N	O>��ϔt��.R�<��V�K�)�J��Z�=t0�G'�
ӈ��'R%Ja�3ǂ���-��w#p�=ᡴ��s����3jr
�i�(bAG#�:2�g:�nK!*\�������b�� }��X�n��A��]B͂�>�W��*,�[	_
^4M�[��Jwye~�5�^8�=��W���O�,h鋬y�K�|s�g� ���@8�ʪ�v�Eu��z �N�oq=���\N��]��+Z�Fq@��yyx�������� J.�u[6�T�7<V�}��Ȇ-LmkGA_ㆤv���eu� =���`�ڔ�h���?��pH E#�v=�-4���Ƌ�����ʜ���-?�P�8�]B�ۋ���Ŏ]0��i�7i:�77�jB��ٓqS�~��|��9ї�e�-,wOH���wݥ�Ҽӽ>@��z�o7k�$�����ԏq�Y��O�q��߉MB����0�t�J=���	������̙'���7<$���7F绢4�MT|2��ή���B�'q.�YEc�����%�!��7#���}���ܲ�+�����U)�D���Nf���C����DV<t5�oj�7��XO��6��~��MFƝ~�L��8���!��&�ٙ_�i�f$s~Q^ =Õ�K>Au4�EO���*hbF��j5�L���OW���H��EH�zި}NT��@�>��8Zp]Ȧ�F2���[���m��XM4&b��J��u�m��pa�����������I��.��uFO���ߨ����������$��X*?���jJ딓���KO��&$="�r�Gw\.�!өI����T�Y���T��r��� ���Me�Jr��~7�LO�R���,[�5��"؇������X����8`�b�ʈJĥ�_�#f^\��E��7C�ڮ���I�#�)�.�{���O\&Vo^'��*�t�V��֏���u�]Z�Ƭµ�����!)>��kGJ��I��48��ͣ]��� �6�S�ҕ�+�+�Y>h�����u������\s��+��^���H��@kE\��M���o����B�$�~�n--
 /�<[���T|T���bu������$4��૸�f@��Lv{L.���"N��2��<��<5��4���8l���S[����'4P7a��L�=>��XES$I`��P_A`�IL1��r�8�f��Y��U[A�~q����S����*��@���b�5c�ʄK{�Nv��ǮU�x\n��%Fo;�)�Uن�)OZ�|��ڣ^1V�G�B�?TT�Պ�G�z?7|��)���<�ypO͚B2X;�ڶ�#Ԍ{z���\v#����e�%7%
��M��P\OU�&�]��^Wߡ�����νo�r���{�r��W\2_L�ݜ�T�k8b�|_Yx3O���V���zw���8�u�?���x�M/�������x����U5f�+^�o��ˀFxD��C9�E~}�X���'4���m�^�����r��=e���S7�z�k��X���>͘qd��ä������gsl�s�a/73�������E��y��7�����N�Q��sx?����*�S�wbn���^�GS?jc��L�ڿs9�z������4�:\Φ�ѝ_��vka~a��y�+� !�w����o�����>~/�O��O���~�>tu�ybΠ��k��&_8��Yj-,��t&���¯C�4:;���	�d�f�6'-�\� ��]i/�����g8��$a�3ߚ_^^�N���j�L���B�Q?š��$�E"�4�^X�>�Ձ��n��'_m>|�nI(      o      x������ � �      p   4   x�3���q�wt����2�p�B�\���!��G77.'4(���� �(;      r   |   x�}�1�0��+�[wg�T�KEE�D� ّ�i(�|\���V��!`d6D��&�q�0Zύ�����Y��R����]zHy�}�
��^i���#��m���u�QD\K�a 4�ZV�UJ�Ϡ+      t   �
  x��ZM���>k~Ŝ�K����,m֛xW�Dʮ�����,E��XW��>E��A`؍a�ְ,�ȁ�����3�V���&�ȁ-�B�<3���EMoimMSTU����G�}�Pm]׮�I����gy9{�O��~Kk�(�R�%�MUm릥;�SC�!���C|T�LС�d!�\ǚ��$L�S�)D��nI!}�[9�g��e����=�$��z�>�W������r��\�^���-��%���0!G4P>NSf>	V�T�X^�Ȑ3�:��C<*g?Mj\�(���zZ�@��M�y��9>����C?)g��d4	�l��r]��Q�S!UG������'8-޺8-�.>N�W�lSd�K*$b#c�X�I�?�X�	��kݳ����V�yJ7M�^3MC��nfḜ��L?OR�LE�:QG
�V���8/;ˀ<��� ou�0���o\Xn9;�\ފ����:r[��'���������!'������;؉��C�j}��͔�iL� ����a..lu�6��Fp	A4��<�:
"��:�GQ<{;{]���3�|�QdʦOb���'��N�4t�p��iK�؆�/��pPO�L@b ҥ$#\m���2u]��_��=��	����R�|9r�k��,]&���dL!8愾����V?)C�&$�*���w����� �W�Zv�E�T����9r��_O����#����`WBd���T!���~��r���nmD�R]E�\���z$·��lW�)�X6r�D���{ m_���ј��y�D��+���}����4^l'�p�����(Sp�#�bU�s�Ju�0�"ZnQ�S�7L����K�N�����V���1���ٖ��� MN�z�Q�x��Y\u�t�X���B�/1��<d[�B�dw�Ƌ	�)p�y�z����f��T:@EN�����ކ�=*�;�1��� �jCz@*k����F\�шqRm�	��,'>�W��G��,�WE���]p�>���[����Q��w!M:U��دj���M�%jirZ�0 ���)+�A$yav��49���}�A���~V��8�}p���L���G�@H�j#]�j+��`�y@i̫�=�%�D�hH�3��Q`�a`�|�+L9n*7k������#S�UX>} �a:Y�Vy43��(�Tp��: �wv��0�
+z�B�t^�,��>gI*�"H1tH<@�g¿����=�F�L����5��-f�P>B���>(=s&�g4zK��M��H�1��	���@��?&̕�F�\�������2����ա_�	�GX�����T�,R��������쀭�6 �H�1�K�d��������&mD��*��P��u՞:h@t`�2�`��%�tx���dB�#l��6�)�x�G�w3�^m���L9�@RLxe����%�!���	W�]?8�G(�(̼T��F�㫄LGsd�eBm��xCh�i+J&y@�v��UVz\.�{��a�h}�^�SL}ya S.��Vk&>�|L��	K�"����B�m !��	�GbLYr��6+DauS��DGT�c#k�𢱞A�x��O��rY�u؏�
!�씳W�b�݄��]]�1<<K.�W�.OZ�֭��b�W���������]eq��b8H9e�,���߫����=�۰}pzr�s�xzl��*c�7�Y�A�����'8���	���p���o$�%���<UviF��hȒnm�:qp����bS鴑%'5=H��`�8E���Q�Yf�<�=T�좗�#��BE��kV�ԸIkP?�k��3��������łF��E#X� [�h��1�+�xӈ윂l�3�Bl����0w�!�c�rU�rf[�b��f��M��Ж��z���Zvj�s9F��pr[;vu�b�>0��z��:����X���{�	dâ��?���p��C2�c�7���h�������:rq���J��0 C������B֫6[[t�~H���F9��=���=���@d����&�#'f���e����x�?���.X�1��q�	�-�NSF��a���������A\c�?�˙8�����9�}�2�h*A�r_�}c�xh_L�'4�Nc#�-!��=�8�.L$����zI���Y�xQ�h�ܬg�!ٮ�ˍn�6^N�H�4���hH.`^������XlQ+d�
49���dki�hƦe/'�l��G�zm��Ԅ�An��=����Ii���)K
p����<�4J�WmŪߺ�;���z���=�����ū���E��_]\ut��w$7�ccq]�)���{e��3"`(���_4eG|���9��$F�4��=^Cr������������b�%�U�t�5�U�p�EoX�%����P�|v��fg�i���Iβ%=��aQ�=X�����%���.³�D�����r<h�x�l{mo���w����uL?���sBp��Y>�UE��྾���8�ª?����绮��d²̯%�u�H6��*r]�Q�0�^�Q�xW�zU �'�S��Ma�vUn�Ru�vyǩ��]�em�ʍ ����Z�]�o��L�ɍ��-����j,,�˭�r��L�]F��j4.c�-j�g쭌-r?�]!���!j�*њ��$���n"������      v      x������ � �      x      x�3�4�2�4�2c#�=... ��      y   �   x����
�0���Sx�qw��ēFT�VP����`+��֧�AR� �s�]�����>��n��$(e�tr���o/�sEV]2W���zi�G�=���0Z�@!H��<9��A{���9��A�Z�iYu4^�c��%��O��Iz������C������z785Tl(�|CT e���c|D3��9���w#     