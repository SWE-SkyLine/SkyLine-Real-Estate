import styles from './page.module.css';
import Head from 'next/head';
import LoginForm from './Login/page';

export default function Home() {
    return (
        <>
            <Head>
                <title>Skyline</title>
                <meta name="description" content="" />
                <meta name="viewport" content="width=device-width, initial-scale=1" />
                <link rel="icon" href="/favicon.ico" />
            </Head>
            <main>
            {/* <LoginForm /> */}
            </main>
        </>
    )
}