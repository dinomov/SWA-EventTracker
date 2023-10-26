import * as React from 'react';
import { Create, SimpleForm, TextInput, required, BooleanInput, UrlField } from 'react-admin';

export const ApiCreate = () => (
    <Create>
        <SimpleForm>
            <TextInput source="url" multiline={true} validate={[required()]} fullWidth />
            <TextInput source="apiKey" multiline={true} validate={[required()]} fullWidth />
            <BooleanInput source='needskey' defaultChecked/>
        </SimpleForm>
    </Create>
);